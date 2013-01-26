package ggjsap2013.models;

import java.util.List;

import ggjsap2013.Gloomy;
import ggjsap2013.controllers.io.LevelReader;
import ggjsap2013.controllers.io.MapBuilder;
import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.score.Score;
import ggjsap2013.models.snake.SnakeModel;

public class Stage {
    private final SnakeModel snake;
    private final Score score = new Score();
    private final MapModel map = new MapModel(Gloomy.STAGE_WIDTH, Gloomy.STAGE_HEIGHT);
    private boolean isGameOver = false;
    private LevelReader levelReader = new LevelReader();
    private List<Level> levelInfoList = levelReader.read();
    private int currentStageIndex = 0;
    
    public Stage()
	{
    	setLevel(currentStageIndex);
    	snake = new SnakeModel(this);
	}
    
    public Level getCurrentLevel() {
        return levelInfoList.get(currentStageIndex);
    }
    
    public void nextLevel() {
        currentStageIndex++;
        setLevel(currentStageIndex);
    }
    
    public void setLevel(int level) {
        MapModel levelMap = MapBuilder.create(levelInfoList.get(level));
        map.mergeMap(levelMap.getArray());
    }
    
    public MapModel getMap() {
        return map;
    }
    
    public SnakeModel getSnake() {
        return snake;
    }
    
    public Score getScore() {
        return score;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
}
