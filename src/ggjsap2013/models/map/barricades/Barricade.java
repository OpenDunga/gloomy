package ggjsap2013.models.map.barricades;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;

/**
 * 障害物だーー
 * 
 * @author Casamorica
 *
 */
public class Barricade implements Block
{
	public static enum TYPES {
		STONE("S"),
		ROCK("R"),
		BOMB("B"),
		WALL("W"),
		BACHI_BACHI("8");
		
		private String shortName;
		private TYPES(String shortName)
		{
			this.shortName = shortName;
		}
	}
	
	public static TYPES getType(String shortName)
	{
		TYPES type = null;
		for (TYPES t : TYPES.values()) {
			if (t.shortName.equals(shortName)) {
				type = t;
			}
		}
		if (type == null) {
			throw new IllegalArgumentException();
		}
		
		return type;
	}
	
	
	
	/**
	 * 種類
	 */
	private TYPES type;
	
	
	
	/**
	 * 種類を与えてインスタンス作るよ
	 * 
	 * @param type
	 */
	public Barricade(TYPES type)
	{
		this.type = type;
	}

	
	/**
	 * 種類取得するよ
	 * 
	 * @return
	 */
	public TYPES getType()
	{
		return type;
	}

	@Override
	public void intersects(SnakeModel snake, MapModel map, Stage stage)
		throws GameOverException
	{
		switch (type) {
			case STONE:
				/* 一人減るよ */
				snake.getBodies().killHead();
				break;
				
			case ROCK:
				/* 二人減るよ */
				snake.getBodies().killHead();
				snake.getBodies().killHead();
				break;
				
			case BOMB:
				/* 四人減るよ */
				snake.getBodies().killHead();
				snake.getBodies().killHead();
				snake.getBodies().killHead();
				snake.getBodies().killHead();
				break;
				
			case WALL:
				/* 一人減るよ */
				snake.getBodies().killHead();
				break;
				
			case BACHI_BACHI:
				/* 三人減るよ */
				snake.getBodies().killHead();
				snake.getBodies().killHead();
				snake.getBodies().killHead();
				break;
		}
	}
	
	
	@Override
	public String toString()
	{
		return "X-" + type.shortName;
	}
	
	
}
