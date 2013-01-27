package ggjsap2013.models.snake;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Wall;
import ggjsap2013.models.skill.Skill;
import ggjsap2013.models.skill.Skill.TYPES;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SnakeModel {
	
	/**
	 * 障害物衝突後の無敵状態の制限時間(2秒)
	 */
	public static final int		COLLISION_DAMAGE_ZERO_LIMIT	= 100;
	

	
	/**
	 * ゆっくり状態の移動速度(1秒に1マス)
	 */
	public static final int		SLOW_MOVE_WAIT				= 50;
	
	
	
	
	
	
    public enum Direction {NORTH, EAST, SOUTH, WEST}
    private final MapModel map;
    private final Stage stage;
    private final BodyList bodies;
    private final MovedHistories movedHistories = new MovedHistories();

    
    /**
     * 通常状態の移動速度
     */
    private int defaultMoveWait = 10;
    
    /**
     * 現在の移動速度
     */
    private int moveWait = 10;
    
    
    private Direction direction = Direction.SOUTH;
    private Direction nextDirection = Direction.SOUTH;
    
    
    /**
     * 障害物衝突後の無敵状態フラグ
     */
    private boolean isCollisionDamageZero;
    
    /**
     * 障害物衝突後の無敵状態残りフレーム数
     */
    private int		collisionDamageZeroCount;
    
    /**
     * 現在発動中のスキル
     */
    private Skill	currentSkill;
    
    /**
     * 現在発動中の残りフレーム数
     */
    private int		skillCount = 0;
    
    
    public SnakeModel(Stage stage) {
        this.stage = stage;
        this.bodies = new BodyList(stage);
        this.map = stage.getMap();
        
        List<SnakeBody> bodies = new ArrayList<SnakeBody>();
        for(int i=0; i<2; i++) {
            bodies.add(new SnakeBody(BodyType.Alice));
            bodies.add(new SnakeBody(BodyType.Mermaid));
            bodies.add(new SnakeBody(BodyType.Akazukin));
        }
        getBodies().init(bodies);

        movedHistories.push(new Point(5, 5), Direction.SOUTH);
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
            stage.gameOver();
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
    	setDirection(nextDirection);
    	
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
        	/* 壁じゃない場合のみブロックを消す */
        	if (b instanceof Wall == false) {
                map.getArray()[p.y][p.x] = null;
        	}
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
    
    public Point getHeadPosition()
    {
    	return movedHistories.get(0).getPoint();
    }
    
    
    /**
     * 指定された種類のキャラを含むかどうかを返します
     * 
     * @param type
     * @return
     */
    public boolean contains(BodyType type)
    {
    	boolean isContains = false;
    	for (int i=0; i<bodies.size(); i++) {
    		SnakeBody body = bodies.get(i);
    		if (body.getType() == type) {
    			isContains = true;
    			break;
    		}
    	}
    	
    	return isContains;
    }

    public int getDefaultMoveWait()
	{
		return defaultMoveWait;
	}
    
    public void setDefaultMoveWait(int defaultMoveWait)
	{
		this.defaultMoveWait = defaultMoveWait;
		this.moveWait = defaultMoveWait;
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
    
    public void setNextDirection(Direction nextDirection)
	{
		this.nextDirection = nextDirection;
	}
    
    
    /**
     * 障害物衝突後の無敵状態を発動しますよー
     * 
     */
    public void invokeCollisionDamageZero()
    {
    	if (isCollisionDamageZero == false) {
        	collisionDamageZeroCount = COLLISION_DAMAGE_ZERO_LIMIT;
        	isCollisionDamageZero = true;
    	}
    }
    
    /**
     * 障害物衝突後の無敵状態の制限時間を減らしていきます
     */
    public void decreaseCollisionDamageZeroCount()
    {
    	if (collisionDamageZeroCount > 0) {
    		collisionDamageZeroCount--;
    		
    		if (collisionDamageZeroCount <= 0) {
    			isCollisionDamageZero = false;
    		}
    	}
    }
    
    
    /**
     * 障害物衝突後の無敵状態かどうかを返します
     * 
     * @return
     */
    public boolean isCollisionDamageZero()
	{
		return isCollisionDamageZero;
	}
    
    
    /**
     * 指定されたスキルを発動します
     * 
     * @param skill
     * @return 
     */
    public boolean invokeSkill(Skill skill)
    {
    	boolean isInvoked = false;
    	
    	/* 現在スキル未発動のときのみ */
    	if (currentSkill == null) {
    		currentSkill = skill;
    		
    		switch (skill.getType()) {
    			case DAMAGE_ZERO:
    				skillCount = Skill.NO_DAMAGE_SKILL_LIMIT;
    				isInvoked = true;
    				break;
    				
    			case SLOW:
    				skillCount = Skill.SLOW_SKILL_LIMIT;
    				setMoveWait(SLOW_MOVE_WAIT);
    				isInvoked = true;
    				break;
    				
    			case BREAK:
    				skillCount = Skill.BREAK_SKILL_LIMIT;
    				isInvoked = true;
    				break;
    				
    			case POINT_DOUBLE:
    				skillCount = Skill.POINT_DOUBLE_SKILL_LIMIT;
    				isInvoked = true;
    				break;
    				
    			case FRIEND_POINT:
    				skillCount = Skill.FRIEND_POINT_SKILL_LIMIT;
    				isInvoked = true;
    				break;
    				
    			default:
    				isInvoked = false;
    				break;
    		}
    	}
    	
    	return isInvoked;
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
        				setMoveWait(defaultMoveWait);
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
    
    
    /**
     * 障害物破壊のスキルが発動中かどうかを返します
     * 
     * @return
     */
    public boolean isBreaking()
    {
    	if (currentSkill == null) {
    		return false;
    	} else if (currentSkill.getType() == TYPES.BREAK) {
    		return true;
    	} else {
    		return false;
    	}
    	
    }
    
    
    /**
     * ポイント２倍状態が発動中かどうかを返します
     * 
     * @return
     */
    public boolean isPointDouble()
    {
    	if (currentSkill == null) {
    		return false;
    	} else if (currentSkill.getType() == TYPES.POINT_DOUBLE) {
    		return true;
    	} else {
    		return false;
    	}
    }

    /**
     * メンバーポイント獲得のスキル発動中かどうかを返しますよ
     * 
     * @return
     */
    public boolean isFriendPoint()
    {
    	if (currentSkill == null) {
    		return false;
    	} else if (currentSkill.getType() == TYPES.FRIEND_POINT) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
}
