package ggjsap2013.views.title;

import ggjsap2013.views.game.GameScene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
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
    private final Rectangle selector = new Rectangle();
    private int selectID = 1;

    public Title() {
        this.add(new Fade(null, null).fadeIn(200));
        this.setKeyWait(20);
        BGMPlayer.getInstance().setMedia("ggjsap2013/resources/sounds/title.wav");
        BGMPlayer.getInstance().play();
    }

    public void drawNode(NodeGraphics g) {
        g.drawGameImage(Images.get("title_back"));
        g.setColor(Color.blue);
        g.drawRect(selector);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g.drawText("Start", 360, 425);
        g.drawText("Exit", 365, 505);
    }

    boolean isTransition = false;
    @Override
    protected void listenKeys(boolean[] keys) {
        if(isTransition)
            return;
        if(keys[KeyEvent.VK_SPACE]) {
            execMenu(selectID);
        } else if(keys[KeyEvent.VK_UP]) {
            selectID--;
            if(selectID < 1)
                selectID = 2;
        } else if(keys[KeyEvent.VK_DOWN]) {
            selectID++;
            if(selectID > 2)
                selectID = 1;
        }
        setSelector(selectID);
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

    private void setSelector(int selectID) {
        if(selectID == 1) {
            selector.setBounds(345, 420, 100, 50);
        } else if(selectID == 2) {
            selector.setBounds(345, 500, 100, 50);
        }
    }

}
