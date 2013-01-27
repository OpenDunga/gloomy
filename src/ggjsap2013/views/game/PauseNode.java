package ggjsap2013.views.game;

import ggjsap2013.Gloomy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.sounds.BGMPlayer;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class PauseNode extends GameNode {
    private final GameScene scene;
    private final Color bgColor = new Color(0,0,0,100);
    
    //1~6
    private int page = 1;

    public PauseNode(GameScene scene) {
        this.scene = scene;
        this.setKeyWait(30);
    }
    
    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, Gloomy.CHIP_SIZE * Gloomy.STAGE_WIDTH, Gloomy.CHIP_SIZE * Gloomy.STAGE_HEIGHT);
        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));

//        g.drawText("Pause!", 230, 220);
//        g.drawText("Press Space to restart.", 150, 270);
    }

    @Override
    protected void listenKeys(boolean[] keys) {
        if(keys[KeyEvent.VK_SPACE]) {
            scene.gamePause(false);
        }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}

}
