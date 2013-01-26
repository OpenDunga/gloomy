package ggjsap2013.models.map;

import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.item.CharacterItem;
import ggjsap2013.models.map.item.PointItem;
import ggjsap2013.utils.RandomUtil;

import java.util.List;

public class MapModel {
	
    private final Block[][] map;
    
    private final Level		currentLevel;
    
    public MapModel(int width, int height, Level level) {
        map = new Block[height][width];
        currentLevel = level;
    }
    
    /**
     * レベル情報に基づいてマップモデル初期化するよ！
     * 
     */
    public void init()
    {
    	for (int i=0; i<currentLevel.getMaxPointItemCount(); i++) {
    		createPointItemBlock();
    	}
    	for (int i=0; i<currentLevel.getMaxCharacterItemCount(); i++) {
    		createCharacterItemBlock();
    	}
    }

    public Block[][] getArray() {
        return map;
    }
    
    
    /**
     * 指定された位置にブロック配置するよー
     * 
     * @param x
     * @param y
     * @param b
     */
    public void setBlock(int x, int y, Block b)
    {
    	map[y][x] = b;
    }
    
    /**
     * ステージ情報を読み取り、ランダムな位置に新規ポイントアイテムブロックを生成する.
     */
    public void createPointItemBlock() 
    {
    	
    	while (true) {
        	int putX = RandomUtil.nextInt(map[0].length);
        	int putY = RandomUtil.nextInt(map.length);
        	
        	if (map[putY][putX] != null) {
        		continue;
        	}
        	
        	List<PointItem.TYPES> typeList = currentLevel.getAvailablePointItemTypes();
        	PointItem.TYPES itemType = typeList.get(RandomUtil.nextInt(typeList.size()));
        	
            map[putY][putX] = new PointItem(itemType);
            break;
    	}
    }
    

    /**
     * ステージ情報を読み取り、ランダムな位置に新規キャラアイテムブロックを生成する.
     */
    public void createCharacterItemBlock() 
    {
    	
    	while (true) {
        	int putX = RandomUtil.nextInt(map[0].length);
        	int putY = RandomUtil.nextInt(map.length);
        	
        	if (map[putY][putX] != null) {
        		continue;
        	}
        	
        	List<CharacterItem.TYPES> typeList = currentLevel.getAvailableCharacterItemTypes();
        	CharacterItem.TYPES itemType = typeList.get(RandomUtil.nextInt(typeList.size()));
        	
            map[putY][putX] = new CharacterItem(itemType);
            break;
    	}
    }
}
