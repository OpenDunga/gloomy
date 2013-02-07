package ggjsap2013.controllers.io;

import ggjsap2013.models.Stage;
import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Barricade;

import java.util.Map;

/**
 * レベルからマップを作成する.
 * @author tohhy
 */
public class MapBuilder {
    
    /**
     * レベルを受け取ってそのレベル向けに初期化されたマップを生成して返す.
     */
    public static MapModel create(Stage stage, Level level) {
        MapModel map = new LevelMapReader().readLevelMap(level.getIndex(), level);
        addRandomBlocks(stage, level, map);
        return map;
    }
    
    /**
     * レベル情報に基づいてマップモデル初期化するよ！
     */
    private static void addRandomBlocks(Stage stage, Level level, MapModel map)
    {
        for (int i=0; i<level.getMaxPointItemCount(); i++) {
            map.createPointItemBlock(stage, level);
        }
        for (int i=0; i<level.getMaxCharacterItemCount(); i++) {
            map.createCharacterItemBlock(stage, level);
        }
        
        for (Map.Entry<Barricade.TYPES, Integer> entry : level.getBarricades().entrySet()) {
        	for (int i=0; i<entry.getValue(); i++) {
        		map.createBarricadeBlock(stage, level, entry.getKey());
        	}
        }
    }
}
