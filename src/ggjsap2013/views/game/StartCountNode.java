package ggjsap2013.views.game;

import java.awt.Color;
import java.awt.Font;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class StartCountNode extends GameNode {
    private final GameScene scene;
    private final Color bgColor = new Color(255,255,255,100);
    private int passedFrames = 0;
    
    public StartCountNode(GameScene scene) {
        this.scene = scene;
    }

    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        if(passedFrames < 50) {
            g.drawText("3", 330, 220);
        } else if(passedFrames < 75) {
            g.drawText("2", 330, 220);
        } else {
            g.drawText("1", 330, 220);
        }
        passedFrames++;
        if(passedFrames > 100) {
            start();
        }
    }

    @Override
    protected void listenKeys(boolean[] keys) {
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}
    
    private void start() {
        scene.pause(false);
        scene.gamePause(false);
        this.dispose();
    }

}
