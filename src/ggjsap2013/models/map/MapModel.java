package ggjsap2013.models.map;

import ggjsap2013.models.Stage;
import ggjsap2013.models.level.Level;
import ggjsap2013.models.map.barricades.Barricade;
import ggjsap2013.models.map.item.CharacterItem;
import ggjsap2013.models.map.item.PointItem;
import ggjsap2013.models.snake.BodyType;
import ggjsap2013.utils.RandomUtil;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class MapModel {	
    private final Block[][] map;

    public MapModel(int width, int height) {
        map = new Block[height][width];
    }
    
    
    /**
     * 指定された障害物をランダムな位置に配置します
     * 
     * @param currentLevel
     * @param type
     */
    public void createBarricadeBlock(Stage stage, Level currentLevel, Barricade.TYPES type)
    {
    	 while (true) {
             int putX = RandomUtil.nextInt(map[0].length);
             int putY = RandomUtil.nextInt(map.length);
             
             if (map[putY][putX] != null) {
                 continue;
             }
             
             Point snakeHeadPos = stage.getSnake().getHeadPosition();
             if (snakeHeadPos.x == putX && snakeHeadPos.y == putY) {
            	 continue;
             }
             
             map[putY][putX] = new Barricade(type);
             break;
         }
    }
    
    
    /**
     * ステージ情報を読み取り、ランダムな位置に新規ポイントアイテムブロックを生成する.
     */
    public void createPointItemBlock(Stage stage, Level currentLevel) 
    {
        while (true) {
            int putX = RandomUtil.nextInt(map[0].length);
            int putY = RandomUtil.nextInt(map.length);
            
            if (map[putY][putX] != null) {
                continue;
            }
            
            Point snakeHeadPos = stage.getSnake().getHeadPosition();
            if (snakeHeadPos.x == putX && snakeHeadPos.y == putY) {
           	 continue;
            }
 
            
            List<PointItem.TYPES> typeList = currentLevel.getAvailablePointItemTypes();
            PointItem.TYPES itemType = typeList.get(RandomUtil.nextInt(typeList.size()));
            
            map[putY][putX] = new PointItem(itemType);
            break;
        }
    }
    
    /**
     * ステージ情報を読み取り、ランダムな位置に新規キャラアイテムブロックを生成する.
     */
    public void createCharacterItemBlock(Stage stage, Level currentLevel) 
    {
        while (true) {
            int putX = RandomUtil.nextInt(map[0].length);
            int putY = RandomUtil.nextInt(map.length);
            
            if (map[putY][putX] != null) {
                continue;
            }
            
            Point snakeHeadPos = stage.getSnake().getHeadPosition();
            if (snakeHeadPos.x == putX && snakeHeadPos.y == putY) {
            	continue;
            }
            
            List<String> typeList = currentLevel.getAvailableCharacterItemTypes();
            String typeString = typeList.get(RandomUtil.nextInt(typeList.size()));
            
            CharacterItem.TYPES itemType = null;
            if (typeString.equals("H")) {
            	itemType = CharacterItem.HEROINES[RandomUtil.nextInt(CharacterItem.HEROINES.length)];
            	
                /* 既にマップ上に同じキャラアイテムが存在するか */
            	boolean hasSameItem = false;
                for(int i=0; i<map.length; i++) {
                    for(int j=0;j<map[0].length; j++) {
                    	Block b = map[i][j];
                    	if (b != null && b instanceof CharacterItem) {
                    		CharacterItem charItem = (CharacterItem)b;
                    		if (charItem.getType() == itemType) {
                    			hasSameItem = true;
                    			break;
                    		}
                    	}
                    }
                }
                
                if (hasSameItem) {
                	itemType = CharacterItem.ANIMALS[RandomUtil.nextInt(CharacterItem.ANIMALS.length)];
                }
            	
            } else {
            	itemType = CharacterItem.ANIMALS[RandomUtil.nextInt(CharacterItem.ANIMALS.length)];
            }
            
            
            
            /* 隊列に既にヒロインキャラが含まれていた場合、ランダムで動物アイテムを配置しますよ */
            if (CharacterItem.isHeroineType(itemType)) {
                BodyType bodyType = CharacterItem.getBodyTypeFromItemType(itemType);
                boolean b = stage.getSnake().contains(bodyType);
                if (b) {
                	itemType = CharacterItem.ANIMALS[RandomUtil.nextInt(CharacterItem.ANIMALS.length)];
                }
            }
            
            map[putY][putX] = new CharacterItem(itemType);
            break;
        }
    }
    
    /**
     * 引数に渡されたmapのnullでない値を現在のMapに上書きする.
     */
    public void mergeMap(Block[][] toMerge) {
        assert(toMerge.length == map.length && 
                toMerge[0].length == map[0].length);
        
        for(int i=0; i<map.length; i++) {
            for(int j=0;j<map[0].length; j++) {
                if(toMerge[i][j] != null)
                    map[i][j] = toMerge[i][j];
            }
        }
    }
    
    public void clear() {
        for(int i=0; i<map.length; i++) {
            for(int j=0;j<map[0].length; j++) {
                map[i][j] = null;
            }
        }
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
     * 指定されたブロックの位置取得
     * 
     * @param targetBlock
     * @return
     */
    public Point getBlockPosition(Block targetBlock)
    {
    	Point pos = null;
        for(int i=0; i<map.length; i++) {
            for(int j=0;j<map[0].length; j++) {
            	Block b = map[i][j];
            	if (b == targetBlock) {
            		pos = new Point(j, i);
            		break;
            	}
            }
        }
        
        return pos;
    }
    
    
    /**
     * ランダムでマップ中にいる移動中の障害物を破壊する
     * 
     */
    public void breakOneMovingBarricade()
    {
    	List<Barricade> movingBarricade = new ArrayList<Barricade>();
    	
        for(int i=0; i<map.length; i++) {
            for(int j=0;j<map[0].length; j++) {
                if(map[i][j] instanceof Barricade) {
                    Barricade b = (Barricade) map[i][j];
                    
                    if (b.isMovable()) {
                    	movingBarricade.add(b);
                    }
                }
            }
        }
        
        if (movingBarricade.size() > 0) {
        	Barricade target = movingBarricade.get(RandomUtil.nextInt(movingBarricade.size()));
        	Point pos = getBlockPosition(target);
        	
        	map[pos.y][pos.x] = null;
        }
    }
    
    
}
