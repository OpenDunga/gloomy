package ggjsap2013.models;

import ggjsap2013.Gloomy;
import ggjsap2013.controllers.io.LevelReader;
import ggjsap2013.controllers.io.MapBuilder;
import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.score.Score;
import ggjsap2013.models.snake.SnakeModel;

import java.util.List;

public class Stage {
    private final SnakeModel snake;
    private final Score score = new Score(this);
    private final MapModel map = new MapModel(Gloomy.STAGE_WIDTH, Gloomy.STAGE_HEIGHT);
    private boolean isGameOver = false;
    private LevelReader levelReader = new LevelReader();
    private List<Level> levelInfoList = levelReader.read();
    private int currentLevelNum = 0;
    
    public Stage()
	{
    	setLevel(currentLevelNum);
    	snake = new SnakeModel(this);
	}
    
    public Level getCurrentLevel() {
        return levelInfoList.get(currentLevelNum);
    }
    
    public void nextLevel() {
        currentLevelNum++;
        setLevel(currentLevelNum);
    }
    
    public void setLevel(int level) {
        MapModel levelMap = MapBuilder.create(currentLevelNum, levelInfoList.get(level));
        map.clear();
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

    public int getCurrentLevelNum() {
        return currentLevelNum;
    }

    public void setCurrentLevelNum(int currentLevelNum) {
        this.currentLevelNum = currentLevelNum;
    }
}
