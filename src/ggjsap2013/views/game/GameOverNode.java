package ggjsap2013.views.game;

import ggjsap2013.Gloomy;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.sounds.BGMPlayer;
import jp.tohhy.gamepanel.sounds.SEPlayer;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class GameOverNode extends GameNode {
    private final GameScene scene;
    private final Color bgColor = new Color(255,255,255,150);

    public GameOverNode(GameScene scene) {
        this.scene = scene;
        this.setKeyWait(30);
        BGMPlayer.getInstance().stop();
        SEPlayer.play("ggjsap2013/resources/sounds/finish.wav");
    }
    
    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, Gloomy.CHIP_SIZE * Gloomy.STAGE_WIDTH, Gloomy.CHIP_SIZE * Gloomy.STAGE_HEIGHT);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 26));
        g.drawText("Game Over!", 210, 200);
        g.drawText("Press space to go to Result", 120, 270);
    }

    @Override
    protected void listenKeys(boolean[] keys) {
        if(keys[KeyEvent.VK_SPACE]) {
            scene.add(new ResultNode(scene));
            this.dispose();
        }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}
}
