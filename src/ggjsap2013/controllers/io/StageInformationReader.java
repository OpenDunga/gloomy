package ggjsap2013.controllers.io;

import ggjsap2013.models.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.arnx.jsonic.JSON;


/**
 * ステージの設定値的な情報を読み込むクラスですよ
 * 
 * @author Casamorica
 *
 */
public class StageInformationReader extends ConfigurationReader
{
	/**
	 * インスタンス作ります
	 */
	public StageInformationReader()
	{
	}
	
	
	/**
	 * 読み込みます。
	 * 
	 * @return {@link Stage}の{@link List}
	 */
	public List<Stage> read()
	{
		ArrayList<Stage> stageList = new ArrayList<Stage>();
		
		try {
			Stage[] stageArray = JSON.decode(readConfiguration("stages.json"), Stage[].class);
			
			if (stageArray != null) {
				for (Stage stage: stageArray) {
					if (stage != null) {
						stageList.add(stage);
					}
				}
			}
			
			Collections.sort(stageList,
				new Comparator<Stage>() {
					@Override
					public int compare(Stage s1, Stage s2)
					{
						return s1.getIndex() - s2.getIndex();
					}
				}
			);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		
		return stageList;
	}
	
	public static void main(String[] args)
	{
		StageInformationReader reader = new StageInformationReader();
		reader.read();
	}
	
	
}
