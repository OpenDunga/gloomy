package ggjsap2013.models.map.item;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.BodyType;
import ggjsap2013.models.snake.SnakeBody;
import ggjsap2013.models.snake.SnakeModel;


/**
 * キャラクターアイテム
 * 
 * @author Casamorica
 *
 */
public class CharacterItem implements Item
{
	

	public static enum TYPES {
		A,
		B,
		C
	}
	
	
	private final TYPES type;
	
	
	/**
	 * 指定された種類でインスタンス作成するよ。
	 * 
	 * @param type
	 */
	public CharacterItem(TYPES type)
	{
		this.type = type;
	}
	
	public TYPES getType()
	{
		return type;
	}

	@Override
	public void intersects(SnakeModel snake, MapModel map)
			throws GameOverException
	{
		switch (getType()) {
			case A:
				snake.getBodies().addBody(new SnakeBody(BodyType.A));
				break;
			case B:
				snake.getBodies().addBody(new SnakeBody(BodyType.B));
				break;
			case C:
				snake.getBodies().addBody(new SnakeBody(BodyType.C));
				break;
		}
		map.createCharacterItemBlock();
	}
	
	@Override
	public String toString()
	{
		return type + "さん";
	}
}
