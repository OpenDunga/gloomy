package ggjsap2013.models.map.item;

import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;

/**
 * ポイントアイテム
 * 
 * @author Casamorica
 *
 */
public class PointItem implements Item
{
	
	public static enum TYPES {
		CANDY,
		DONUT,
		RIBON,
		CAKE,
		JEWEL
	}
	
	
	private final TYPES type;
	
	
	
	/**
	 * 指定された種類でインスタンス生成するよ。
	 * 
	 * @param type
	 */
	public PointItem(TYPES type)
	{
		this.type = type;
	}
	
	public TYPES getType()
	{
		return this.type;
	}
	
	
	@Override
	public void intersects(SnakeModel snake, MapModel map)
	{
		switch (getType()) {
			case CANDY:
				break;
				
			case DONUT:
				break;
				
			case RIBON:
				break;
				
			case CAKE:
				break;
			
			case JEWEL:
				break;
		}
		
		map.createRandomBlock();
	}
	
	@Override
	public String toString()
	{
		return type.toString();
	}
	
}
