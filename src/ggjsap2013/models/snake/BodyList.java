package ggjsap2013.models.snake;


import ggjsap2013.exceptions.GameOverException;
import ggjsap2013.models.Stage;

import java.util.LinkedList;
import java.util.List;

public class BodyList {
    private final Stage stage;
    private static BodyType[] ANIMALS = 
        {BodyType.ahiru, BodyType.kuma, BodyType.lion, BodyType.panda, BodyType.usagi};
    private final LinkedList<SnakeBody> list = new LinkedList<SnakeBody>();
    
    public BodyList(Stage stage) {
        this.stage = stage;
    }

    /**
     * Bodyを追加.もしそのBodyがすでに隊列にいればランダムに動物を追加する.
     * @param body
     */
    public void addBody(SnakeBody body) {
        //キャラカウントを加算
        stage.getScore().charaCountUp();
        //隊列にキャラ追加
        if(hasBody(body.getType())) {
            list.addFirst(createRandomAnimal());
        } else {
            list.addFirst(body);
        }
    }
    
    /**
     * 指定のSnakeBodyのリストで初期化する.
     * キャラカウントには影響を与えない
     * @param bodies
     */
    public void init(List<SnakeBody> bodies) {
        list.clear();
        for(SnakeBody b:bodies) 
            if(hasBody(b.getType())) {
                list.addLast(createRandomAnimal());
            } else {
                list.addLast(b);
            }
    }
    
    private boolean hasBody(BodyType body) {
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
    private SnakeBody createRandomAnimal() {
        int animalIndex = (int)(ANIMALS.length * Math.random());
        return new SnakeBody(ANIMALS[animalIndex]);
    }
    
    /**
     * ヘビの頭を切り落とすよ
     * @throws GameOverException 
     * 
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
     * 頭を返す.
     * @return
     */
    public SnakeBody getHead() {
        if(list.size() == 0) return null;
        return list.getFirst();
    }
    
    /**
     * 先頭を後ろに回す
     */
    public void changeForward() {
        SnakeBody head = list.pollFirst();
        list.add(head);
    }
    
    /**
     * 最後尾を前に回す.
     */
    public void changeBackward() {
        SnakeBody tail = list.pollLast();
        list.addFirst(tail);
    }
    
    public int size() {
        return list.size();
    }
    
    public SnakeBody get(int i) {
        return list.get(i);
    }
}
