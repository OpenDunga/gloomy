package ggjsap2013.utils;

import java.util.Random;


/**
 * 乱数生成用のユーティリティ！
 * 
 * @author Casamorica
 *
 */
public class RandomUtil
{
	private static final Random random = new Random(System.currentTimeMillis());
	
	/**
	 * インスタンスは生成しないでおｋ
	 */
	private RandomUtil()
	{
		
	}
	
	
	public static int nextInt(int n)
	{
		return random.nextInt(n);
	}
	
	
}
