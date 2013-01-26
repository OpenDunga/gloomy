package ggjsap2013.views;

import java.awt.Color;
import java.awt.Font;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class GameOverNode extends GameNode {

    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(new Color(255,255,255,100));
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        g.drawText("Game Over!", 200, 220);
    }

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}

}
