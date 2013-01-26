package ggjsap2013.models.snake;

import java.awt.Point;
import java.util.LinkedList;

/**
 * スネークが通過した地点のリスト.
 * @author tohhy
 */
public class MovedPoints {
    private final LinkedList<Point> list = new LinkedList<Point>();
    //限界の長さ.ゲームでの制限値を指定
    private int limitLength = 100;
    
    /**
     * 指定座標がしっぽとぶつかっているかの判定.
     * ヘビの長さと判定するポイントを指定し、交差していればtrue
     * @return
     */
    public boolean isIntersect(int length, Point p) {
        if(length <= 1) return false;
        for(int i=1; i<length; i++) {
            if(list.size() <= i) break;
            if(list.get(i).equals(p)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * リストの先頭に座標を追加する.
     */
    public void push(Point p) {
        list.push(p);
        while(list.size() > limitLength) {
            list.pollLast();
        }
    }
    
    /**
     * リストの長さを返す.
     * @return
     */
    public int size() {
        return list.size();
    }
    
    /**
     * 指定インデックスのPointを返す.
     * @param i
     * @return
     */
    public Point get(int i) {
        return list.get(i);
    }
}
