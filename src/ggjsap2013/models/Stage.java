package ggjsap2013.models;

import ggjsap2013.Gloomy;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.score.Score;
import ggjsap2013.models.snake.SnakeModel;

public class Stage {
    private final MapModel map = new MapModel(Gloomy.STAGE_WIDTH, Gloomy.STAGE_HEIGHT);
    private final SnakeModel snake = new SnakeModel(getMap());
    private final Score score = new Score();
    
    public MapModel getMap() {
        return map;
    }
    
    public SnakeModel getSnake() {
        return snake;
    }
    
    public Score getScore() {
        return score;
    }
}
