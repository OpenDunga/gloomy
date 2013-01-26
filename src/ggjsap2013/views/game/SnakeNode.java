package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.Stage;
import ggjsap2013.models.snake.BodyList;
import ggjsap2013.models.snake.BodyType;
import ggjsap2013.models.snake.MovedHistories;
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
        MovedHistories history = model.getHistories();
        BodyList bodies = model.getBodies();
        if(length <= 0) {
            //長さゼロはゲームオーバー
            g.setColor(Color.red);
            g.fillRect(history.get(0).getPoint().x*CHIP_SIZE, 
                    history.get(0).getPoint().y*CHIP_SIZE, 
                    CHIP_SIZE, CHIP_SIZE);
        } else {
            g.setColor(Color.blue);
            for(int i=0; i < length; i++) {
                if(i >= history.size()) break;
                Point p = history.get(i).getPoint();
                Direction d = history.get(i).getDirection();
                SnakeBody b = bodies.get(i);
                g.drawRect(
                        p.x*CHIP_SIZE, p.y*CHIP_SIZE, 
                        CHIP_SIZE, CHIP_SIZE);
                drawBody(b, p, d, g);
                g.setColor(Color.red);
            }
        }
    }
    
    private void drawBody(SnakeBody b, Point p, Direction d, NodeGraphics g) {
        switch(b.getType()) {
        case A:
            g.drawGameImage(getCharaImage(b, d), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
            break;
        default:
            if (model.isNoDamage()) {
                g.drawText("**" + b.getType().toString(), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
            } else {
                g.drawText(b.getType().toString(), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
            }
        }
    }
    
    private GameImage getCharaImage(SnakeBody b, Direction d) {
        String imageName = "chara_";
        switch(b.getType()) {
        case A:
            imageName = imageName + "1_";
            break;
        default:
            imageName = imageName + "1_";
        }
        switch(d) {
        case NORTH:
            imageName = imageName + "n";
            break;
        case SOUTH:
            imageName = imageName + "s";
            break;
        case EAST:
            imageName = imageName + "e";
            break;
        case WEST:
            imageName = imageName + "w";
            break;
        }
        return Images.get(imageName);
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
        
        model.decreaseNoDamageCount();
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
