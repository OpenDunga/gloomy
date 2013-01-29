package ggjsap2013.models.map.barricades;

import jp.tohhy.gamepanel.sounds.SEPlayer;
import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;

public class Wall implements Block {

    @Override
    public void intersects(SnakeModel snake, MapModel map, Stage stage)
            throws GameOverException 
    {
    	
    	if (snake.isCollisionDamageZero() || snake.isNoDamage()) {
    		
    	} else {
    	    //サウンド再生
            SEPlayer.play("ggjsap2013/resources/se/damage.wav");
            snake.getBodies().killHeads(255);
    	}
    }

    @Override
    public int getMoveWait() {
        return 0;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public void setCurrentMove(int n) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getCurrentMove() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void move(SnakeModel snakeModel, MapModel mapModel, int currentX, int currentY) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return "WALL";
    }
}
