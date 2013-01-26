package ggjsap2013.models.map.barricades;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;

public class Wall implements Block {

    @Override
    public void intersects(SnakeModel snake, MapModel map, Stage stage)
            throws GameOverException {
        killHeads(snake, 255);
    }
    
    private void killHeads(SnakeModel snake, int times) throws GameOverException {
        for(int i=0; i<times; i++) {
            snake.getBodies().killHead();
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
    public void move(MapModel mapModel, int currentX, int currentY) {
        // TODO Auto-generated method stub
    }

    @Override
    public String toString() {
        return "WALL";
    }
}
