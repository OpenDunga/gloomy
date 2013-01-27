package ggjsap2013.controllers.io;

import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Wall;

import java.io.IOException;

import net.arnx.jsonic.JSON;

/**
 * 各レベルのマップ情報を読み込むクラス
 * 
 * @author Casamorica
 *
 */
public class LevelMapReader extends ConfigurationReader
{
	
	/**
	 * インスタンス作るよ
	 */
	public LevelMapReader()
	{
	}
	
    /**
     * ステージ情報を読み込みましょう
     * 
     * @param stageIndex ステージインデックス
     * @return {@link MapModel}
     */
    public MapModel readLevelMap(int levelNum, Level level)
    {
        
        try {
            String[][] stageArray = JSON.decode(readConfiguration("map_type_" + level.getMapType() + ".json"), String[][].class);
            
            int width = 0;
            int height = 0;
            width = stageArray.length;
            for (int i=0; i<width; i++) {
                height = Math.max(height, stageArray[i].length);
            }
            
            MapModel mapModel = new MapModel(width, height);
            
            for (int y=0; y<height; y++) {
                for (int x=0; x<width; x++) {
                    try {
                        if(stageArray[y][x].equals("W")) {
                            mapModel.setBlock(x, y, new Wall());
                        }
//                        TYPES type = Barricade.getType(stageArray[y][x]);
//                        Barricade barricade = new Barricade(type);
                        
                    } catch (Exception e) {
                        //例外は障害物じゃないよ
                    }
                }
            }
            return mapModel;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

	
}
