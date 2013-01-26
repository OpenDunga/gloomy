package ggjsap2013.controllers.io;

import ggjsap2013.models.Stage;
import ggjsap2013.models.level.Level;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.arnx.jsonic.JSON;


/**
 * レベルの設定値的な情報を読み込むクラスですよ
 * 
 * @author Casamorica
 *
 */
public class LevelReader extends ConfigurationReader
{
	/**
	 * インスタンス作ります
	 */
	public LevelReader()
	{
	}
	
	
	/**
	 * 読み込みます。
	 * 
	 * @return {@link Stage}の{@link List}
	 */
	public List<Level> read()
	{
		ArrayList<Level> levelList = new ArrayList<Level>();
		
		try {
			Level[] levelArray = JSON.decode(readConfiguration("levels.json"), Level[].class);
			
			if (levelArray != null) {
				for (Level level: levelArray) {
					if (level != null) {
						levelList.add(level);
					}
				}
			}
			
			Collections.sort(levelList,
				new Comparator<Level>() {
					@Override
					public int compare(Level s1, Level s2)
					{
						return s1.getIndex() - s2.getIndex();
					}
				}
			);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		
		return levelList;
	}
	
	public static void main(String[] args)
	{
		LevelReader reader = new LevelReader();
		reader.read();
	}
	
	
}