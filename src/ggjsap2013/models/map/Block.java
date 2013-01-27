package ggjsap2013.models.map;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.snake.SnakeModel;

public interface Block {
    
    /**
     * このブロックに対してSnakeの頭が乗った際の処理を記述する.
     */
    public void intersects(SnakeModel snake, MapModel map, Stage stage)
    	throws GameOverException;
    
    
    /**
     * 移動の待ち時間
     * 
     * @return
     */
    public int getMoveWait();
    
    
    /**
     * 移動可能か？
     * 
     * @return
     */
    public boolean isMovable();
    
    
    /**
     * 現在の移動値を設定
     * 
     * @param n
     */
    public void setCurrentMove(int n);
    
    
    /**
     * 現在の移動値を取得
     * 
     * @return
     */
    public int getCurrentMove();
    
    
    /**
     * ブロックを移動
     * 
     */
    public void move(SnakeModel snakeModel, MapModel mapModel, int currentX, int currentY);
    
}
