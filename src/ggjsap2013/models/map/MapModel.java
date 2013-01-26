package ggjsap2013.models.map;

import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.barricades.Barricade;
import ggjsap2013.models.map.item.CharacterItem;
import ggjsap2013.models.map.item.PointItem;
import ggjsap2013.utils.RandomUtil;

import java.util.List;

public class MapModel {	
    private final Block[][] map;

    public MapModel(int width, int height) {
        map = new Block[height][width];
    }
    
    
    /**
     * ステージ情報を読み取り、ランダムな位置に新規障害物ブロックを生成する.
     */
    public void createBarricadeBlock(Level currentLevel) 
    {
        while (true) {
            int putX = RandomUtil.nextInt(map[0].length);
            int putY = RandomUtil.nextInt(map.length);
            
            if (map[putY][putX] != null) {
                continue;
            }
            
            List<Barricade.TYPES> typeList = currentLevel.getAvailableBarricadeTypes();
            Barricade.TYPES barricadeType = typeList.get(RandomUtil.nextInt(typeList.size()));
            
            map[putY][putX] = new Barricade(barricadeType);
            break;
        }
    }
    
    /**
     * ステージ情報を読み取り、ランダムな位置に新規ポイントアイテムブロックを生成する.
     */
    public void createPointItemBlock(Level currentLevel) 
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
    public void createCharacterItemBlock(Level currentLevel) 
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
    
    /**
     * 引数に渡されたmapのnullでない値を現在のMapに上書きする.
     */
    public void mergeMap(Block[][] toMerge) {
        assert(toMerge.length == map.length && 
                toMerge[0].length == map[0].length);
        
        for(int i=0; i<map.length; i++) {
            for(int j=0;j<map[0].length; j++) {
                if(toMerge[i][j] != null)
                    map[i][j] = toMerge[i][j];
            }
        }
    }
    
    public void clear() {
        for(int i=0; i<map.length; i++) {
            for(int j=0;j<map[0].length; j++) {
                map[i][j] = null;
            }
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
}
