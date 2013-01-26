package ggjsap2013.controllers.io;

import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Barricade;
import ggjsap2013.models.map.barricades.Barricade.TYPES;

import java.io.IOException;

import net.arnx.jsonic.JSON;

/**
 * ステージ情報を読み込むクラス
 * 
 * @author Casamorica
 *
 */
public class StageReader extends ConfigurationReader
{
	
	/**
	 * インスタンス作るよ
	 */
	public StageReader()
	{
	}
	
	
	/**
	 * ステージ情報を読み込みましょう
	 * 
	 * @param stageIndex ステージインデックス
	 * @return {@link MapModel}
	 */
	public MapModel read(int stageIndex)
	{
		MapModel mapModel = null;
		
		try {
			String[][] stageArray = JSON.decode(readConfiguration("stage" + stageIndex + ".json"), String[][].class);
			
			int width = 0;
			int height = 0;
			height = stageArray.length;
			for (int i=0; i<height; i++) {
				width = Math.max(width, stageArray[i].length);
			}
			
			mapModel = new MapModel(width, height);
			
			for (int y=0; y<height; y++) {
				for (int x=0; x<width; x++) {
					try {
						TYPES type = Barricade.TYPES.valueOf(stageArray[x][y]);
						Barricade barricade = new Barricade(type);
						mapModel.setBlock(x, y, barricade);
					} catch (Exception e) {
						//例外は障害物じゃないよ
					}
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		
		return mapModel;
	}
	
	public static void main(String[] args)
	{
		(new StageReader()).read(0);
	}
	
}
