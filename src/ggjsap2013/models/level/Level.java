package ggjsap2013.models.level;

import ggjsap2013.models.map.item.Item;

import java.util.List;

public class Level
{
	
	/**
	 * ステージの順番
	 * 
	 */
	private int					index;
	
	/**
	 * マップ中に出てくるアイテムの最大数だよ
	 */
	private int					maxItemCount;
	

	/**
	 * マップ中に出てくる可能性のあるアイテムの種類ですよー
	 */
	private List<Item.TYPES>	availableItemTypes;
	
	
	
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
	
	
	public void setMaxItemCount(int maxItemCount)
	{
		this.maxItemCount = maxItemCount;
	}
	public int getMaxItemCount()
	{
		return maxItemCount;
	}
	
	
	public void setAvailableItemTypes(List<Item.TYPES> availableItemTypes)
	{
		this.availableItemTypes = availableItemTypes;
	}
	public List<Item.TYPES> getAvailableItemTypes()
	{
		return availableItemTypes;
	}
}
