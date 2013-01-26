package ggjsap2013.models.map.barricades;

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
		A,
		B,
		C
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
	public void intersects(SnakeModel snake, MapModel map)
	{
		switch (type) {
			case A:
				snake.getBodies().killHead();
				break;
			case B:
				snake.getBodies().killHead();
				break;
			case C:
				snake.getBodies().killHead();
				break;
		}
	}
	
	
	@Override
	public String toString()
	{
		return "X-" + type;
	}
	
	
}
