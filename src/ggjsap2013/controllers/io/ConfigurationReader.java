package ggjsap2013.controllers.io;

import ggjsap2013.Gloomy;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 設定値読み込みの基底クラス
 * 
 * @author Casamorica
 *
 */
public abstract class ConfigurationReader
{
	/**
	 * インスタンスを作ります
	 */
	public ConfigurationReader()
	{
	}
	
	
	/**
	 * resources/configs/以下のファイルを文字列で読み込みます。
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	protected String readConfiguration(String fileName)
		throws IOException
	{
		String configFileContent = "";
		
		InputStream in = null;
		ByteArrayOutputStream out = null;
		
		try {
			in = new BufferedInputStream(ConfigurationReader.class.getClassLoader().getResourceAsStream("ggjsap2013/resources/configs/" + fileName), Gloomy.BUFFER_SIZE);
			out = new ByteArrayOutputStream();
			
			byte[] buffer = new byte[Gloomy.BUFFER_SIZE];
			
			while (true) {
				int length = in.read(buffer);
				if (length < 0) {
					break;
				}
				out.write(buffer, 0, length);
			}
			
			out.flush();
			
			configFileContent = new String(out.toByteArray(), Gloomy.FILE_ENCODING);
			
			
		} catch (IOException e) {
			throw e;
		} finally {
			IOException exception = null;
			
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					exception = e;
				}
			}
			
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					exception = e;
				}
			}
			
			if (exception != null) {
				throw exception;
			}
		}
		
		return configFileContent;
	}
}
