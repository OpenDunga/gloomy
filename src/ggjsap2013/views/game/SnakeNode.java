package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.Stage;
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
import jp.tohhy.gamepanel.images.GameImage;
import jp.tohhy.gamepanel.images.Images;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class SnakeNode extends GameNode {
    private final Stage stage;
    private final SnakeModel model;
    private static final int CHIP_SIZE = Gloomy.CHIP_SIZE;
    private int currentMoveWait = 0;

    public SnakeNode(Stage stage) {
        this.stage = stage;
        this.model = stage.getSnake();
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
            //長さゼロはゲームオーバー
            g.setColor(Color.red);
            g.fillRect(points.get(0).x*CHIP_SIZE, points.get(0).y*CHIP_SIZE, 
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
                drawBody(b, p, g);
                g.setColor(Color.red);
            }
        }
    }
    
    private void drawBody(SnakeBody b, Point p, NodeGraphics g) {
        switch(b.getType()) {
        case A:
            g.drawGameImage(getCharaImage(b), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
            break;
        default:
            g.drawText(b.getType().toString(), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
        }
    }
    
    private GameImage getCharaImage(SnakeBody b) {
        GameImage i = Images.get("chara_1_w");
        return i;
    }

    @Override
    protected void updateNode() {
        if(stage.isGameOver()) {
            this.freeze(true);
        }
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
            if(keys[KeyEvent.VK_Z]) {
                model.getBodies().changeForward();
            }
            if(keys[KeyEvent.VK_X]) {
                model.getBodies().changeBackward();
            }
            if(keys[KeyEvent.VK_C]) {
                //TODO ポーズ、ヘルプ表示
            }
            if(keys[KeyEvent.VK_SPACE]) {
                //TODO スキル発動
            }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

}
