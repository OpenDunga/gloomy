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
	

	
	/**
	 * 無敵状態の制限時間(10秒)
	 */
	public static final int		NO_DAMAGE_SKILL_LIMIT		= 500;
	
	/**
	 * ゆっくり状態の制限時間(10秒)
	 */
	public static final int		SLOW_SKILL_LIMIT			= 500;
	
	/**
	 * 障害物破壊の制限時間(10秒)
	 */
	public static final int		BREAK_SKILL_LIMIT			= 500;
	
	/**
	 * ポイント２倍の制限時間(10秒)
	 * 
	 */
	public static final int		POINT_DOUBLE_SKILL_LIMIT	= 500;
	
	/**
	 * メンバーポイント獲得の制限時間(10秒)
	 * 
	 */
	public static final int		FRIEND_POINT_SKILL_LIMIT	= 500;
	
	
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
		boolean isInvoked = false;
		
		switch (type) {
			case DAMAGE_ZERO:
				isInvoked = snake.invokeSkill(this);
				break;
				
			case SLOW:
				isInvoked = snake.invokeSkill(this);
				break;
				
			case BREAK:
				isInvoked = snake.invokeSkill(this);
				map.breakOneMovingBarricade();
				break;
				
			case POINT_DOUBLE:
				isInvoked = snake.invokeSkill(this);
				break;
				
			case FRIEND_POINT:
				isInvoked = snake.invokeSkill(this);
				if (isInvoked) {
					int size = snake.getBodies().size();
					stage.getScore().addScore(size * 1000);
				}
				break;
			
		}
		
		
		if (isInvoked) {
			try {
				snake.getBodies().killHead();
			} catch (GameOverException e) {
	            stage.gameOver();
			}
		}
	}
}
