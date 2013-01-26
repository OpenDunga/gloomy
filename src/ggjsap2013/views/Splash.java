package ggjsap2013.views;

import java.awt.Color;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.nodes.transition.Fade;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * Kawazロゴ表示画面.
 * @author tohhy
 */
public class Splash extends GameNode {
    private int passedFrame = 0;
    
    public Splash() {
        this.add(new Fade(null, null).setColor(Color.white).fadeIn(80));
    }

    @Override
    protected void drawNode(NodeGraphics g) {
        
    }

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {
        if(passedFrame > 160) {
            this.add(new Fade(this, new Title()).fadeOut(100).setAutoFadeIn(50, 50));
            this.passedFrame = 0;
        }
        passedFrame++;
    }
}
