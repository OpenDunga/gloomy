package ggjsap2013.controllers.io;

import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Barricade;
import ggjsap2013.models.map.barricades.Barricade.TYPES;

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
	public MapModel read(int stageIndex, Level level)
	{
		MapModel mapModel = null;
		
		try {
			String[][] stageArray = JSON.decode(readConfiguration("level" + stageIndex + ".json"), String[][].class);
			
			int width = 0;
			int height = 0;
			width = stageArray.length;
			for (int i=0; i<width; i++) {
				height = Math.max(height, stageArray[i].length);
			}
			
			mapModel = new MapModel(width, height, level);
			
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					try {
						TYPES type = Barricade.getType(stageArray[y][x]);
						Barricade barricade = new Barricade(type);
						mapModel.setBlock(x, y, barricade);
					} catch (Exception e) {
						//例外は障害物じゃないよ
					}
				}
			}
			
			mapModel.init();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		
		return mapModel;
	}
	
	public static void main(String[] args)
	{
	}
	
}
