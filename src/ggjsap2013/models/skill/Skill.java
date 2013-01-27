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
		DAMAGE_ZERO("一定時間無敵"),
		SLOW("スピード低下"),
		BREAK("敵キャラクター破壊"),
		POINT_DOUBLE("ポイント２倍"),
		FRIEND_POINT("メンバー数×1000ポイント獲得");
		
		private String description;
		
		private TYPES(String description) {
		    this.description = description;
		}
		
		public String getDescription() {
            return description;
        }
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
					snake.invokeSkill(this);
					snake.getBodies().killHead();
					break;
					
				case SLOW:
					snake.invokeSkill(this);
					snake.getBodies().killHead();
					break;
					
				case BREAK:
					snake.invokeSkill(this);
					map.breakOneMovingBarricade();
					snake.getBodies().killHead();
					break;
					
				case POINT_DOUBLE:
					snake.invokeSkill(this);
					snake.getBodies().killHead();
					break;
					
				case FRIEND_POINT:
					snake.invokeSkill(this);
					int size = snake.getBodies().size();
					stage.getScore().addScore(size * 1000);
					snake.getBodies().killHead();
					break;
				
			}
		} catch (GameOverException e) {
            stage.gameOver();

		}
	}
}
