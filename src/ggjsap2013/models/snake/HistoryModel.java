package ggjsap2013.models.snake;

import ggjsap2013.models.snake.SnakeModel.Direction;

import java.awt.Point;

/**
 * 移動した地点の記憶用.
 * @author tohhy
 */
public class HistoryModel {
    private final Point point;
    private final Direction direction;
    
    public HistoryModel(Point p, Direction d) {
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
