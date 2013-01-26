package ggjsap2013.models.level;

import ggjsap2013.models.map.item.PointItem;

import java.util.List;

public class Level
{
	
	/**
	 * ステージの順番
	 * 
	 */
	private int					index;
	
	/**
	 * マップ中に出てくるポイントアイテムの最大数だよ
	 */
	private int					maxPointItemCount;
	

	/**
	 * マップ中に出てくる可能性のあるアイテムの種類ですよー
	 */
	private List<PointItem.TYPES>	availableItemTypes;
	
	
	
	public Level()
	{
	}
	
	public void setIndex(int index)
	{
		this.index = index;
	}
	public int getIndex()
	{
		return index;
	}
	
	
	public void setMaxPointItemCount(int maxPointItemCount)
	{
		this.maxPointItemCount = maxPointItemCount;
	}
	public int getMaxPointItemCount()
	{
		return maxPointItemCount;
	}
	
	
	public void setAvailableItemTypes(List<PointItem.TYPES> availableItemTypes)
	{
		this.availableItemTypes = availableItemTypes;
	}
	public List<PointItem.TYPES> getAvailableItemTypes()
	{
		return availableItemTypes;
	}
}
