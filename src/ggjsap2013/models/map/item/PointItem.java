package ggjsap2013.models.map.item;

import ggjsap2013.models.Stage;
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
	public void intersects(SnakeModel snake, MapModel map, Stage stage)
	{
		switch (getType()) {
			case CANDY:
				/* キャンディーは100点 */
				stage.getScore().addScore(100);
				break;
				
			case DONUT:
				/* ドーナッツは500点 */
				stage.getScore().addScore(500);
				break;
				
			case RIBON:
				/* リボンは1000点 */
				stage.getScore().addScore(1000);
				break;
				
			case CAKE:
				/* ケーキは5000点 */
				stage.getScore().addScore(5000);
				break;
			
			case JEWEL:
				/* 宝石10000点 */
				stage.getScore().addScore(10000);
				break;
		}
		
		map.createPointItemBlock(stage.getCurrentLevel());
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
	public void move(MapModel mapModel, int currentX, int currentY)
	{
	}
	
	
	@Override
	public String toString()
	{
		return type.toString();
	}
	
}
