package ggjsap2013.models.skill;

import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;


/**
 * キャラが使うスキル
 * 
 * @author Casamorica
 *
 */
public class Skill
{
	public static enum TYPES {
		DAMAGE_ZERO,
		SLOW,
		BREAK,
		POINT_DOUBLE,
		FRIEND_POINT
	}
	
	
	private TYPES type;
	
	
	/**
	 * 種類を指定してインスタンス作ります
	 * 
	 * @param type
	 */
	public Skill(TYPES type)
	{
		this.type = type;
	}
	
	
	public TYPES getType()
	{
		return type;
	}
	
	
	
	/**
	 * スキルじっこーーー
	 * 
	 * @param snake
	 * @param map
	 * @param stage
	 */
	public void invoke(SnakeModel snake, MapModel map, Stage stage)
	{
		try {
			switch (type) {
				case DAMAGE_ZERO:
					snake.invokiSkill(this);
					snake.getBodies().killHead();
					break;
					
				case SLOW:
					break;
					
				case BREAK:
					break;
					
				case POINT_DOUBLE:
					break;
					
				case FRIEND_POINT:
					break;
				
			}
		} catch (GameOverException e) {
            // TODO ゲームオーバー処理
            stage.setGameOver(true);
            System.out.println("GameOver!");

		}
	}
}
