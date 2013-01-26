package ggjsap2013.models.map;

import ggjsap2013.models.map.item.Item;
import ggjsap2013.models.map.item.Item.TYPES;

public class MapModel {
    private final Block[][] map;
    
    public MapModel(int width, int height) {
        map = new Block[height][width];
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
    
    /**
     * ステージ情報を読み取り、ランダムな位置に新規ブロックを生成する.
     */
    public void createRandomBlock() 
    {
        //TODO ステージ情報の読み取り
        int putX = (int)(map[0].length * Math.random());
        int putY = (int)(map.length * Math.random());
        map[putY][putX] = new Item(TYPES.A);
    }
}
