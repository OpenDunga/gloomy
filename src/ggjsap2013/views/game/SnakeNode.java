package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.snake.BodyList;
import ggjsap2013.models.snake.BodyType;
import ggjsap2013.models.snake.MovedPoints;
import ggjsap2013.models.snake.SnakeBody;
import ggjsap2013.models.snake.SnakeModel;
import ggjsap2013.models.snake.SnakeModel.Direction;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class SnakeNode extends GameNode {
    private final SnakeModel model;
    private static final int CHIP_SIZE = Gloomy.CHIP_SIZE;
    private int currentMoveWait = 0;

    public SnakeNode(SnakeModel model) {
        this.model = model;
        this.setKeyWait(30);
        for(int i=0; i<2; i++) {
            model.getBodies().addBody(new SnakeBody(BodyType.A));
            model.getBodies().addBody(new SnakeBody(BodyType.B));
            model.getBodies().addBody(new SnakeBody(BodyType.C));
        }
    }

    @Override
    protected void drawNode(NodeGraphics g) {
        int length = model.getLength();
        MovedPoints points = model.getMovedPoints();
        BodyList bodies = model.getBodies();
        if(length <= 0) {
            //長さゼロはゲームオーバー、描画を想定しない
            g.setColor(Color.red);
            g.fillRect(
                    points.get(0).x*CHIP_SIZE, points.get(0).y*CHIP_SIZE, 
                    CHIP_SIZE, CHIP_SIZE);
        } else {
            g.setColor(Color.blue);
            for(int i=0; i < length; i++) {
                if(i >= points.size()) break;
                Point p = points.get(i);
                SnakeBody b = bodies.get(i);
                g.drawRect(
                        p.x*CHIP_SIZE, p.y*CHIP_SIZE, 
                        CHIP_SIZE, CHIP_SIZE);
                g.drawText(b.getType().toString(), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
                g.setColor(Color.red);
            }
        }
    }

    @Override
    protected void updateNode() {
        currentMoveWait++;
        if(model.getMoveWait() <= currentMoveWait) {
            currentMoveWait = 0;
            model.move();
        }
    }

    @Override
    protected void listenKeys(boolean[] keys) {
            //カーソルキーで上下左右に移動
            if(keys[KeyEvent.VK_LEFT]) {
                model.setDirection(Direction.WEST);
            } else if(keys[KeyEvent.VK_RIGHT]) {
                model.setDirection(Direction.EAST);
            }
            if(keys[KeyEvent.VK_UP]) {
                model.setDirection(Direction.NORTH);
            } else if(keys[KeyEvent.VK_DOWN]) {
                model.setDirection(Direction.SOUTH);
            }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

}
