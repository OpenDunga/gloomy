package ggjsap2013.models.snake;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.skill.Skill;
import ggjsap2013.models.skill.Skill.TYPES;

import java.awt.Point;

public class SnakeModel {
	
	/**
	 * 無敵状態の制限時間(1秒)
	 */
	public static final int		NO_DAMAGE_SKILL_LIMIT		= 50;
	
	/**
	 * ゆっくり状態の制限時間(10秒)
	 */
	public static final int		SLOW_SKILL_LIMIT			= 500;
	
	
	/**
	 * 通常状態時の移動速度(1/5秒に1マス)
	 */
	public static final int		DEFAULT_MOVE_WAIT			= 10;
	
	/**
	 * ゆっくり状態の移動速度(1秒に1マス)
	 */
	public static final int		SLOW_MOVE_WAIT				= 50;
	
    public enum Direction {NORTH, EAST, SOUTH, WEST}
    private final MapModel map;
    private final Stage stage;
    private final BodyList bodies = new BodyList();
    private final MovedHistories movedHistories = new MovedHistories();
    private int moveWait = 10;
    private Direction direction = Direction.SOUTH;
    
    private Skill	currentSkill;
    private int		skillCount = 0;
    
    
    public SnakeModel(Stage stage) {
        this.stage = stage;
        this.map = stage.getMap();
        movedHistories.push(new Point(), Direction.SOUTH);
    }
    
    public void move(int dx, int dy) {
        Point gotoPoint = new Point(movedHistories.get(0).getPoint());
        gotoPoint.x += dx;
        gotoPoint.y += dy;
        gotoPoint = getLoopedPoint(gotoPoint);
        this.getHistories().push(gotoPoint, direction);
        try {
            intersectBlock();
            boolean isSelfEat = 
                    movedHistories.isIntersect(bodies.size(), movedHistories.get(0).getPoint());
            if(isSelfEat) throw new GameOverException();
        } catch (GameOverException e) {
            stage.setGameOver(true);
            System.out.println("GameOver!");
        }
    }
    
    /**
     * 引数で受け取った地点がマップ外であれば、それに対応したループ先の地点を返す.
     * マップ内であればそのまま返す.
     * @return
     */
    private Point getLoopedPoint(Point p) {
        if(p.x >= 0 && p.y >= 0 && 
                p.x < map.getArray()[0].length && p.y < map.getArray().length) {
            return p;
        } else {
            Point result = new Point(p.x, p.y);
            while(result.x < 0) result.x = map.getArray()[0].length + result.x;
            while(result.y < 0) result.y = map.getArray().length + result.y;
            while(result.x >= map.getArray()[0].length) result.x = result.x - map.getArray()[0].length;
            while(result.y >= map.getArray().length) result.y = result.y - map.getArray().length;
            return result;
        }
    }
    
    /**
     * 現在の方向へ１マス進行する.
     */
    public void move() {
        switch(direction) {
        case SOUTH:
            move(0, 1);
            break;
        case NORTH:
            move(0, -1);
            break;
        case EAST:
            move(1, 0);
            break;
        case WEST:
            move(-1, 0);
            break;
        }
    }
    
    /**
     * ブロックと交差したときの処理.
     */
    public void intersectBlock() throws GameOverException {
        Point p = movedHistories.get(0).getPoint();
        Block b = map.getArray()[p.y][p.x];
        if(b != null) {
            map.getArray()[p.y][p.x] = null;
            b.intersects(this, map, stage);
        }
    }

    public MovedHistories getHistories() {
        return movedHistories;
    }

    public int getLength() {
        return bodies.size();
    }
    
    public BodyList getBodies() {
        return bodies;
    }

    public int getMoveWait() {
        return moveWait;
    }

    public void setMoveWait(int moveWait) {
        this.moveWait = moveWait;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction)
    {
    	/* 現在の進行方向の逆はセットできないよ！ */
    	boolean isReverse = false;
    	
    	Direction current = this.direction;
    	
    	switch (current) {
    		case NORTH:
    			if (direction == Direction.SOUTH) {
    				isReverse = true;
    			}
    			break;
    			
    		case EAST:
    			if (direction == Direction.WEST) {
    				isReverse = true;
    			}
    			break;
    			
    		case SOUTH:
    			if (direction == Direction.NORTH) {
    				isReverse = true;
    			}
    			break;
    			
    		case WEST:
    			if (direction == Direction.EAST) {
    				isReverse = true;
    			}
    			break;
    	}
    	
    	if (isReverse == false) {
            this.direction = direction;
    	}
    }
    
    
    /**
     * 指定されたスキルを発動します
     * 
     * @param skill
     */
    public void invokiSkill(Skill skill)
    {
    	/* 現在スキル未発動のときのみ */
    	if (currentSkill == null) {
    		currentSkill = skill;
    		
    		switch (skill.getType()) {
    			case DAMAGE_ZERO:
    				skillCount = NO_DAMAGE_SKILL_LIMIT;
    				break;
    			case SLOW:
    				skillCount = SLOW_SKILL_LIMIT;
    				setMoveWait(SLOW_MOVE_WAIT);
    				break;
    		}
    	}
    }
    
    /**
     * 1フレームごとにスキルの制限時間を減らしていきます
     */
    public void decreaseSkillCount()
    {
    	if (skillCount > 0) {
        	skillCount--;
        	if (skillCount <= 0) {
        		/* スキル終了処理 */
        		switch (currentSkill.getType()) {
        			case SLOW:
        				setMoveWait(DEFAULT_MOVE_WAIT);
        				break;
        		}
        		
        		skillCount = 0;
        		currentSkill = null;
        	}
    	}
    }
    
    
    /**
     * スキル発動中かどうかを返しますよ
     * 
     * @return
     */
    public boolean isSkillInvoked()
    {
    	return currentSkill != null;
    }
    
    /**
     * 無敵状態が発動中かどうかを返しますよ
     * 
     * @return
     */
    public boolean isNoDamage()
    {
    	if (currentSkill == null) {
    		return false;
    	} else if (currentSkill.getType() == TYPES.DAMAGE_ZERO) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * ゆっくり状態が発動中かどうかを返しますよ
     * 
     * @return
     */
    public boolean isSlow()
    {
    	if (currentSkill == null) {
    		return false;
    	} else if (currentSkill.getType() == TYPES.SLOW) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
