package ggjsap2013.models.map.barricades;

import ggjsap2013.Gloomy;
import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;
import ggjsap2013.utils.RandomUtil;

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
		BACHI_BACHI("8"),
		SOLDIER("O"),
		KING("K"),
		JOKER("J"),
		DARK_WINDOW("D");
		
		
		private String	shortName;
		
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
	private TYPES	type;
	
	private int		currentMove;
	
	
	/**
	 * 種類を与えてインスタンス作るよ
	 * 
	 * @param type
	 */
	public Barricade(TYPES type)
	{
		this.type = type;
		currentMove = 0;
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
		if (snake.isCollisionDamageZero()) {
			return;
		}
		
		switch (type) {
			case STONE:
				/* 一人減るよ */
			    killHeads(snake, 1);
				break;
				
			case ROCK:
				/* 二人減るよ */
			    killHeads(snake, 2);
				break;
				
			case BOMB:
				/* 四人減るよ */
			    killHeads(snake, 4);
				break;
				
			case WALL:
				/* 一人減るよ */
			    killHeads(snake, 1);
			    //TODO 跳ね返り
				break;
				
			case BACHI_BACHI:
				/* 三人減るよ */
			    killHeads(snake, 3);
				break;

			case SOLDIER:
				/* 一人減るよ */
			    killHeads(snake, 1);
				break;
				
			case KING:
				/* 五人減るよ */
			    killHeads(snake, 5);
				break;

			case JOKER:
				/* 十人減るよ */
			    killHeads(snake, 10);
				break;
        default:
            break;
		}
		
		/* 一定時間無敵になるよ */
		snake.invokeCollisionDamageZero();
		
		/* 無敵スキル発動中に障害物とぶつかった場合は得点もらえるらしい */
		if (snake.isNoDamage()) {
			stage.getScore().addScore(3000);
		}
		
	}
	
	private void killHeads(SnakeModel snake, int times) throws GameOverException {
	    for(int i=0; i<times; i++) {
	        snake.getBodies().killHead();
	    }
	}
	
	@Override
	public int getMoveWait()
	{
		if (isMovable()) {
			return 30;	//TODO:待ち時間てきとー
		} else {
			return 0;
		}
	}
	
	@Override
	public boolean isMovable()
	{
		boolean isMovable = false;
		switch (type) {
			case STONE:
			case ROCK:
			case BOMB:
			case WALL:
			case BACHI_BACHI:
				isMovable = false;
				break;
			
			case SOLDIER:
			case KING:
			case JOKER:
			case DARK_WINDOW:
				isMovable = true;
				break;
				
			default:
				isMovable = false;
				break;
		}
		return isMovable;
	}
	
	@Override
	public void setCurrentMove(int n)
	{
		currentMove = n;
	}
	
	@Override
	public int getCurrentMove()
	{
		return currentMove;
	}
	
	@Override
	public void move(MapModel mapModel, int currentX, int currentY)
	{
		if (isMovable()) {
			//TODO:
			
			int x = currentX;
			int y = currentY;
			
			switch (RandomUtil.nextInt(4)) {
				case 0:
					x++;
					break;
				case 1:
					x--;
					break;
				case 2:
					y++;
					break;
				case 3:
					y--;
					break;
			}
			
			if (0 <= x && x < Gloomy.STAGE_WIDTH) {
				if (0 <= y && y < Gloomy.STAGE_HEIGHT) {
					if (mapModel.getArray()[y][x] == null) {
						mapModel.getArray()[currentY][currentX] = null;
						mapModel.getArray()[y][x] = this;
					}
				}
			}
		}
	}

	
	@Override
	public String toString()
	{
		return type + "";
	}
	
}
