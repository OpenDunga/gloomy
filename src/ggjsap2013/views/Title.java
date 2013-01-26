package ggjsap2013.views;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.nodes.transition.Fade;
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
    }

    public void drawNode(NodeGraphics g) {
        if(getPanel() != null) {
            g.setColor(Color.blue);
            g.drawRect(selector);
        }
    }

    boolean isTransition = false;
    @Override
    protected void listenKeys(boolean[] keys) {
        if(isTransition)
            return;
        if(keys[KeyEvent.VK_ENTER]) {
            execMenu(selectID);
        } else if(keys[KeyEvent.VK_UP]) {
            selectID--;
            if(selectID < 1)
                selectID = 3;
        } else if(keys[KeyEvent.VK_DOWN]) {
            selectID++;
            if(selectID > 3)
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
            getParent().add(new Fade(this, new StageNode()).fadeOut(50).setAutoFadeIn(50, 100));
            break;
        case 2:
            break;
        case 3:
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
            selector.setBounds(345, 340, 100, 50);
        } else if(selectID == 2) {
            selector.setBounds(345, 420, 100, 50);
        } else if(selectID == 3) {
            selector.setBounds(345, 500, 100, 50);
        }
    }

}
