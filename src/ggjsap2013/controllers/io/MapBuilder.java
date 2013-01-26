package ggjsap2013.controllers.io;

import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Barricade;

import java.util.Map;

/**
 * レベル値とレベルからマップを作成する.
 * @author tohhy
 */
public class MapBuilder {
    
    public static MapModel create(Level level) {
        MapModel map = new LevelMapReader().readLevelMap(level.getIndex(), level);
        addRandomBlocks(level, map);
        return map;
    }
    
    /**
     * レベル情報に基づいてマップモデル初期化するよ！
     */
    private static void addRandomBlocks(Level level, MapModel map)
    {
        for (int i=0; i<level.getMaxPointItemCount(); i++) {
            map.createPointItemBlock(level);
        }
        for (int i=0; i<level.getMaxCharacterItemCount(); i++) {
            map.createCharacterItemBlock(level);
        }
        
        for (Map.Entry<Barricade.TYPES, Integer> entry : level.getBarricades().entrySet()) {
        	for (int i=0; i<entry.getValue(); i++) {
        		map.createBarricadeBlock(level, entry.getKey());
        	}
        }
        
//        for (int i=0; i<level.getMaxBarricadeCount(); i++) {
//            map.createBarricadeBlock(level);
//        }
    }
}
