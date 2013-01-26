package ggjsap2013.models.snake;


import java.util.LinkedList;

public class BodyList {
    private final LinkedList<SnakeBody> list = new LinkedList<SnakeBody>();

    public void addBody(SnakeBody body) {
        list.addFirst(body);
    }
    
    /**
     * ヘビの頭を切り落とすよ
     * 
     */
    public void killHead()
    {
    	list.removeFirst();
    }
    
    public int size() {
        return list.size();
    }
    
    public SnakeBody get(int i) {
        return list.get(i);
    }
}
