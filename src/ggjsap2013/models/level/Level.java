package ggjsap2013.models.level;

import ggjsap2013.models.map.item.CharacterItem;
import ggjsap2013.models.map.item.PointItem;

import java.util.List;

public class Level
{
	
	/**
	 * ステージの順番
	 * 
	 */
	private int							index;
	
	
	
	/**
	 * マップ中に出てくるポイントアイテムの最大数だよ
	 */
	private int							maxPointItemCount;
	

	/**
	 * マップ中に出てくる可能性のあるポイントアイテムの種類ですよー
	 */
	private List<PointItem.TYPES>		availablePointItemTypes;
	

	/**
	 * マップ中に出てくるキャラアイテムの最大数だよ
	 */
	private int							maxCharacterItemCount;
	

	/**
	 * マップ中に出てくる可能性のあるキャラアイテムの種類ですよー
	 */
	private List<CharacterItem.TYPES>	availableCharacterItemTypes;
	

	/**
	 * インスタンスつくるよ！
	 */
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
	
	
	public void setAvailablePointItemTypes(List<PointItem.TYPES> availablePointItemTypes)
	{
		this.availablePointItemTypes = availablePointItemTypes;
	}
	public List<PointItem.TYPES> getAvailablePointItemTypes()
	{
		return availablePointItemTypes;
	}
	
	
	public void setMaxCharacterItemCount(int maxCharacterItemCount)
	{
		this.maxCharacterItemCount = maxCharacterItemCount;
	}
	public int getMaxCharacterItemCount()
	{
		return maxCharacterItemCount;
	}
	
	public void setAvailableCharacterItemTypes(
			List<CharacterItem.TYPES> availableCharacterItemTypes)
	{
		this.availableCharacterItemTypes = availableCharacterItemTypes;
	}
	public List<CharacterItem.TYPES> getAvailableCharacterItemTypes()
	{
		return availableCharacterItemTypes;
	}
	
}
