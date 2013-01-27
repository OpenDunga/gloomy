package ggjsap2013.models.map.item;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
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
		C,
		D,
		E
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
	public void intersects(SnakeModel snake, MapModel map, Stage stage)
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
			case D:
				snake.getBodies().addBody(new SnakeBody(BodyType.D));
				break;
			case E:
				snake.getBodies().addBody(new SnakeBody(BodyType.E));
				break;
		}
		map.createCharacterItemBlock(stage.getCurrentLevel());
	}
	
	@Override
	public int getMoveWait()
	{
		return 0;
	}
	
	@Override
	public boolean isMovable()
	{
		return false;
	}
	
	@Override
	public void setCurrentMove(int n)
	{
	}
	@Override
	public int getCurrentMove()
	{
		return 0;
	}
	
	@Override
	public void move(SnakeModel snakeModel, MapModel mapModel, int currentX, int currentY)
	{
	}
	
	
	@Override
	public String toString()
	{
		return type + "さん";
	}
}
