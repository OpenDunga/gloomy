package ggjsap2013.models.snake;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;

import java.awt.Point;

public class SnakeModel {
	
	//TODO:無敵時間
	public static final int		NO_DAMAGE_LIMIT		= 50;
	
	
    public enum Direction {NORTH, EAST, SOUTH, WEST}
    private final MapModel map;
    private final Stage stage;
    private final BodyList bodies = new BodyList();
    private final MovedHistories movedHistories = new MovedHistories();
    private int moveWait = 10;
    private Direction direction = Direction.SOUTH;
    
    private int		currentNoDamageCount = 0;
    
    
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
            // TODO ゲームオーバー処理
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
    
    
    public void invokeNoDamage()
    {
    	currentNoDamageCount = NO_DAMAGE_LIMIT;
    }
    
    public void decreaseNoDamageCount()
    {
    	currentNoDamageCount--;
    	if (currentNoDamageCount <= 0) {
    		currentNoDamageCount = 0;
    	}
    }
    
    public boolean isNoDamage()
    {
    	if (currentNoDamageCount > 0) {
    		return true;
    	} else {
    		return false;
    	}
    }

}
