package ggjsap2013.models.map.item;

import jp.tohhy.gamepanel.sounds.SEPlayer;
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
	

	public static enum TYPES 
	{
		Alice,
		Mermaid,
		Akazukin,
		Shirayuki,
		TinkerBell,
		Ahiru,
	    Kuma,
	    Lion,
	    Panda,
	    Usagi;
	}
	
    public static TYPES[] HEROINES = {TYPES.Alice, TYPES.Mermaid, TYPES.Akazukin, TYPES.Shirayuki, TYPES.TinkerBell};
    public static TYPES[] ANIMALS = {TYPES.Ahiru, TYPES.Kuma, TYPES.Lion, TYPES.Panda, TYPES.Usagi};

	
	public static BodyType getBodyTypeFromItemType(TYPES itemType)
	{
		switch (itemType) {
			case Alice:
				return BodyType.Alice;
			case Mermaid:
				return BodyType.Mermaid;
			case Akazukin:
				return BodyType.Akazukin;
			case Shirayuki:
				return BodyType.Shirayuki;
			case TinkerBell:
				return BodyType.TinkerBell;
				
			case Ahiru:
				return BodyType.ahiru;
			case Kuma:
				return BodyType.kuma;
			case Lion:
				return BodyType.lion;
			case Panda:
				return BodyType.panda;
			case Usagi:
				return BodyType.usagi;
		}
		return null;
	}
	
	public static boolean isHeroineType(TYPES itemType)
	{
		switch (itemType) {
			case Alice:
			case Mermaid:
			case Akazukin:
			case Shirayuki:
			case TinkerBell:
				return true;
				
			case Ahiru:
			case Kuma:
			case Lion:
			case Panda:
			case Usagi:
				return false;
		}
		return false;
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
	    //サウンド再生
        SEPlayer.play("ggjsap2013/resources/se/item2.wav");
        
		stage.getScore().addScore(snake.getLength() * 10);
        
		switch (getType()) {
			case Alice:
				snake.getBodies().addBody(new SnakeBody(BodyType.Alice));
				break;
			case Mermaid:
				snake.getBodies().addBody(new SnakeBody(BodyType.Mermaid));
				break;
			case Akazukin:
				snake.getBodies().addBody(new SnakeBody(BodyType.Akazukin));
				break;
			case Shirayuki:
				snake.getBodies().addBody(new SnakeBody(BodyType.Shirayuki));
				break;
			case TinkerBell:
				snake.getBodies().addBody(new SnakeBody(BodyType.TinkerBell));
				break;
				
			case Ahiru:
				snake.getBodies().addBody(new SnakeBody(BodyType.ahiru));
				break;
			case Kuma:
				snake.getBodies().addBody(new SnakeBody(BodyType.kuma));
				break;
			case Lion:
				snake.getBodies().addBody(new SnakeBody(BodyType.lion));
				break;
			case Panda:
				snake.getBodies().addBody(new SnakeBody(BodyType.panda));
				break;
			case Usagi:
				snake.getBodies().addBody(new SnakeBody(BodyType.usagi));
				break;	
		}
		map.createCharacterItemBlock(stage, stage.getCurrentLevel());
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
