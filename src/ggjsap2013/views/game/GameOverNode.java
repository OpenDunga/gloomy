package ggjsap2013.views.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class GameOverNode extends GameNode {
    private final GameScene scene;
    
    public GameOverNode(GameScene scene) {
        this.scene = scene;
    }

    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(new Color(255,255,255,100));
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g.drawText("Game Over!", 330, 220);
        g.drawText("Press SPACE to restart.", 270, 270);
    }

    @Override
    protected void listenKeys(boolean[] keys) {
        if(keys[KeyEvent.VK_SPACE]) {
            scene.reset();
        }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}

}
