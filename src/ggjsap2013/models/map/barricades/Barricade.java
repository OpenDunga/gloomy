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
		//TODO ぶつかると先頭のキャラが消滅
	}
	
	
}
