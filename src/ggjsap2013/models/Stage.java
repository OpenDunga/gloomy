package ggjsap2013.models;

import ggjsap2013.controllers.io.LevelMapReader;
import ggjsap2013.controllers.io.LevelReader;
import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.score.Score;
import ggjsap2013.models.snake.SnakeModel;

import java.util.List;

public class Stage {
    private final MapModel map;
    private final SnakeModel snake;
    private final Score score = new Score();
    
    public Stage()
	{
    	int currentStageIndex = 0;	//TODO ステージインデックスちゃんとかえること！
    	
    	LevelReader levelReader = new LevelReader();
    	List<Level> levelInfoList = levelReader.read();
    	
    	LevelMapReader levelMapReader = new LevelMapReader();
    	map = levelMapReader.read(currentStageIndex, levelInfoList.get(currentStageIndex));
    	
    	snake = new SnakeModel(getMap());
        
 	
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
}
