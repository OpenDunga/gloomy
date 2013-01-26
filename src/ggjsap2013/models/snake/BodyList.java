package ggjsap2013.models.snake;


import ggjsap2013.exceptions.GameOverException;

import java.util.LinkedList;

public class BodyList {
    private static BodyType[] ANIMALS = 
        {BodyType.ahiru, BodyType.kuma, BodyType.lion, BodyType.panda, BodyType.usagi};
    private final LinkedList<SnakeBody> list = new LinkedList<SnakeBody>();

    /**
     * Bodyを追加.もしそのBodyがすでに隊列にいればランダムに動物を追加する.
     * @param body
     */
    public void addBody(SnakeBody body) {
        if(hasBody(body.getType())) {
            list.addFirst(createRandomAnimal());
        } else {
            list.addFirst(body);
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
