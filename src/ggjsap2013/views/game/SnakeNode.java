package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.Stage;
import ggjsap2013.models.skill.Skill;
import ggjsap2013.models.snake.BodyList;
import ggjsap2013.models.snake.BodyType;
import ggjsap2013.models.snake.MovedHistories;
import ggjsap2013.models.snake.SnakeBody;
import ggjsap2013.models.snake.SnakeModel;
import ggjsap2013.models.snake.SnakeModel.Direction;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

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
        List<SnakeBody> bodies = new ArrayList<SnakeBody>();
        for(int i=0; i<2; i++) {
            bodies.add(new SnakeBody(BodyType.A));
            bodies.add(new SnakeBody(BodyType.B));
            bodies.add(new SnakeBody(BodyType.C));
        }
        model.getBodies().init(bodies);
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
            Point headPoint = history.get(0).getPoint();
            g.drawRect(
                    headPoint.x*CHIP_SIZE, headPoint.y*CHIP_SIZE, 
                    CHIP_SIZE, CHIP_SIZE);
            for(int i=0; i < length; i++) {
                if(i >= history.size()) break;
                Point p = history.get(i).getPoint();
                Direction d = history.get(i).getDirection();
                SnakeBody b = bodies.get(i);
                drawBody(b, p, d, g);
            }
        }
    }
    
    private void drawBody(SnakeBody b, Point p, Direction d, NodeGraphics g) {
        switch(b.getType()) {
        case A:
        case B:
        case ahiru:
        case kuma:
        case lion:
        case panda:
        case usagi:
            GameImage i = getCharaImage(b, d);
            int heightGap = CHIP_SIZE - i.get(0).getHeight();
            g.drawGameImage(i, p.x*CHIP_SIZE, p.y*CHIP_SIZE + heightGap);
            break;
        default:
            if (model.isNoDamage()) {
                g.drawText("**" + b.getType().toString(), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
            } else {
                g.drawText(b.getType().toString(), p.x*CHIP_SIZE, p.y*CHIP_SIZE);
            }
        }
    }
    
    private String createImageName(SnakeBody b, Direction d) {
        String bodyType = "chara";
        int imageID = 0;
        switch(b.getType()) {
        case A:
            imageID = 1;
            break;
        case B:
            imageID = 2;
            break;
        case ahiru:
            imageID = 1;
            bodyType = "animal";
            break;
        case kuma:
            imageID = 2;
            bodyType = "animal";
            break;
        case lion:
            imageID = 3;
            bodyType = "animal";
            break;
        case panda:
            imageID = 4;
            bodyType = "animal";
            break;
        case usagi:
            imageID = 5;
            bodyType = "animal";
            break;
        default:
            imageID = 1;
        }
        String imageName = bodyType + "_" + imageID + "_";
        switch(d) {
        case NORTH:
            return imageName + "n";
        case SOUTH:
            return imageName + "s";
        case EAST:
            return imageName + "e";
        case WEST:
        default:
            return imageName + "w";
        }
    }
    
    private GameImage getCharaImage(SnakeBody b, Direction d) {
        String imageName = createImageName(b, d);
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
        
        model.decreaseSkillCount();
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
            	/* ボディーが２以上のときだけスキル発動 */
            	if (model.getBodies().size() >= 2) {
                    Skill skill = model.getBodies().getHead().getSkill();
                    if (skill != null) {
                    	skill.invoke(model, stage.getMap(), stage);
                    }
            	}
            }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

}
