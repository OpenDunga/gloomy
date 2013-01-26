package ggjsap2013.models.map;

import ggjsap2013.models.snake.SnakeModel;

public interface Block {
    
    /**
     * このブロックに対してSnakeの頭が乗った際の処理を記述する.
     */
    public void intersects(SnakeModel snake, MapModel map);
}
