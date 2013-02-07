package ggjsap2013.models.snake;

import ggjsap2013.models.snake.SnakeModel.Direction;

import java.awt.Point;

/**
 * 単一の移動した地点の情報を記憶するモデル.
 * 地点の座標と、移動した際の方向を保持する
 * @author tohhy
 */
public class HistoryModel {
    private final Point point;
    private final Direction direction;
    
    public HistoryModel(Point p, Direction d) 
    {
        point = p;
        direction = d;
    }

    public Point getPoint() {
        return point;
    }

    public Direction getDirection() {
        return direction;
    }
}
