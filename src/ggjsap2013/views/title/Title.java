package ggjsap2013.views.title;

import ggjsap2013.views.game.GameScene;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.images.Images;
import jp.tohhy.gamepanel.nodes.transition.Fade;
import jp.tohhy.gamepanel.sounds.BGMPlayer;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * タイトル画面.
 * @author tohhy
 */
public class Title extends GameNode {

    public Title() {
        this.add(new Fade(null, null).fadeIn(200));
        this.setKeyWait(20);
        BGMPlayer.getInstance().stop();
        BGMPlayer.getInstance().setMedia("ggjsap2013/resources/sounds/title.wav");
        BGMPlayer.getInstance().play();
    }

    public void drawNode(NodeGraphics g) {
        g.drawGameImage(Images.get("title_back"));
        g.drawGameImage(Images.get("title_logo"));
        g.setColor(Color.black);
        g.fillRect(220, 500, 350, 50);
        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g.drawText("Press Space to Start", 250, 505);
    }

    boolean isTransition = false;
    @Override
    protected void listenKeys(boolean[] keys) {
        if(isTransition)
            return;
        if(keys[KeyEvent.VK_SPACE]) {
            execMenu(1);
        }
    }

    @Override
    protected void listenMouse(MouseInfo arg0) {}

    private void execMenu(int selectID) {
        switch(selectID) {
        case 1:
            isTransition = true;
            getParent().add(new Fade(this, new GameScene()).fadeOut(50).setAutoFadeIn(50, 100));
            break;
        case 2:
            isTransition = true;
            getParent().add(new Fade().fadeOut(50).setAutoFadeIn(0, 0).setMiddleAction(new Runnable() {
                public void run() {
                    System.exit(0);
                }
            }));
        }
    }

    @Override
    protected void updateNode() {}
}
