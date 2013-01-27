package ggjsap2013.models.level;

import ggjsap2013.models.map.barricades.Barricade;
import ggjsap2013.models.map.item.CharacterItem;
import ggjsap2013.models.map.item.PointItem;

import java.util.List;
import java.util.Map;

public class Level
{
	
	/**
	 * レベル番号
	 */
	private int				            index;
	
	
	
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
	
	
	
//	/**
//     * マップ中に出てくる障害物の最大数だよ
//     */
//    private int                         maxBarricadeCount;
//	
//	/**
//     * マップ中に出てくる可能性のある障害物の種類ですよー
//     */
//    private List<Barricade.TYPES>   availableBarricadeTypes;
	
    
    
    private Map<Barricade.TYPES, Integer>	barricades;
    
    
    
    /**
     * ゲームスピード
     */
    private int							gameSpeed;
    
    
    
    /**
     * レベルクリア条件
     */
    private int							levelClearCount;
    
    
    
    /**
     * マップの種類
     */
    private int							mapType;
    
    

	/**
	 * インスタンスつくるよ！
	 */
	public Level() {}
	
	public int getIndex()
	{
		return index;
	}
	
	
	public void setIndex(int index) {
        this.index = index;
    }

    public void setMaxPointItemCount(int maxPointItemCount)
	{
		this.maxPointItemCount = maxPointItemCount;
	}
	public int getMaxPointItemCount()
	{
		return maxPointItemCount;
	}
	
//	public void setAvailableBarricadeTypes(List<Barricade.TYPES> availableBarricadeTypes)
//    {
//        this.availableBarricadeTypes = availableBarricadeTypes;
//    }
//    public List<Barricade.TYPES> getAvailableBarricadeTypes()
//    {
//        return availableBarricadeTypes;
//    }
	
	
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

//    public int getMaxBarricadeCount() {
//        return maxBarricadeCount;
//    }
//
//    public void setMaxBarricadeCount(int maxBarricadeCount) {
//        this.maxBarricadeCount = maxBarricadeCount;
//    }
    
    public void setBarricades(Map<Barricade.TYPES, Integer> barricades)
	{
		this.barricades = barricades;
	}
    public Map<Barricade.TYPES, Integer> getBarricades()
	{
		return barricades;
	}
    
    public void setGameSpeed(int gameSpeed)
	{
		this.gameSpeed = gameSpeed;
	}
    public int getGameSpeed()
	{
		return gameSpeed;
	}
    
    public void setLevelClearCount(int levelClearCount)
	{
		this.levelClearCount = levelClearCount;
	}
    public int getLevelClearCount()
	{
		return levelClearCount;
	}
    
    
    public void setMapType(int mapType)
	{
		this.mapType = mapType;
	}
    public int getMapType()
	{
		return mapType;
	}
	
}
