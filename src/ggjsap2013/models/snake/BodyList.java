package ggjsap2013.models.snake;


import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;

import java.util.LinkedList;
import java.util.List;

/**
 * 蛇の体部分を構成するキャラクタのリスト.
 * 頭から順に蛇を構成しているキャラクタを全て持つ.
 * @author tohhy
 */
public class BodyList {
    private static BodyType[] ANIMALS = {
        BodyType.ahiru, 
        BodyType.kuma, 
        BodyType.lion, 
        BodyType.panda, 
        BodyType.usagi
        };
    private final Stage stage;
    private final LinkedList<SnakeBody> list = new LinkedList<SnakeBody>();
    
    public BodyList(Stage stage) 
    {
        this.stage = stage;
    }

    /**
     * 指定されたBodyを追加する.
     * @param body
     */
    public void addBody(SnakeBody body) 
    {
        //キャラカウントを加算
        stage.getScore().charaCountUp();
        //隊列にキャラ追加
        list.addLast(body);
    }
    
    /**
     * 指定のSnakeBodyのリストで初期化する.
     * キャラカウントには影響を与えない
     * @param bodies
     */
    public void init(List<SnakeBody> bodies) 
    {
        list.clear();
        for(SnakeBody b:bodies) 
            if(hasBody(b.getType())) {
                list.addLast(createRandomAnimal());
            } else {
                list.addLast(b);
            }
    }
    
    /**
     * ヘビの頭を切り落とすよ
     * @throws GameOverException 
     */
    public void killHead()
    	throws GameOverException
    {
    	list.removeFirst();
    	
    	if (list.size() == 0) {
    		throw new GameOverException();
    	}
    }
    
    /**
     * ヘビの頭を指定回数ぶん切り落とすよ
     * @throws GameOverException 
     */
    public void killHeads(int times)
            throws GameOverException
        {
            for(int i=0; i<times; i++) {
                killHead();
            }
        }
    
    /**
     * 蛇の頭になっているSnakeBodyを返す.
     * @return
     */
    public SnakeBody getHead() 
    {
        if(list.size() == 0) return null;
        return list.getFirst();
    }
    
    /**
     * 先頭のSnakeBodyを最後尾に回す.
     */
    public void changeForward() 
    {
        SnakeBody head = list.pollFirst();
        list.add(head);
    }
    
    /**
     * 最後尾のSnakeBodyを先頭に回す.
     */
    public void changeBackward() 
    {
        SnakeBody tail = list.pollLast();
        list.addFirst(tail);
    }
    
    /**
     * この蛇を構成するキャラクタの個数を返す.
     * @return
     */
    public int size() 
    {
        return list.size();
    }
    
    /**
     * この蛇の指定インデックスに存在するキャラクタを返す.
     * @param i
     * @return
     */
    public SnakeBody get(int i) 
    {
        return list.get(i);
    }

    /**
     * 指定したTypeのBodyを持っていればtrue.
     * @param body
     * @return
     */
    private boolean hasBody(BodyType body) 
    {
        for(SnakeBody b : list) {
            if(b.getType() == body) {
                return true;
            }
        }
        return false;
    }

    /**
     * ランダムに動物を生成する.
     * @return
     */
    private SnakeBody createRandomAnimal() 
    {
        int animalIndex = (int)(ANIMALS.length * Math.random());
        return new SnakeBody(ANIMALS[animalIndex]);
    }
}
