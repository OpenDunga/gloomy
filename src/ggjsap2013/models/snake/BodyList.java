package ggjsap2013.models.snake;


import ggjsap2013.exceptions.GameOverException;

import java.util.LinkedList;

public class BodyList {
    private final LinkedList<SnakeBody> list = new LinkedList<SnakeBody>();

    public void addBody(SnakeBody body) {
        list.addFirst(body);
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
    public void changeForword() {
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
