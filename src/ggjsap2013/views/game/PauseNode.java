package ggjsap2013.views.game;

import java.awt.Color;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.sounds.BGMPlayer;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class PauseNode extends GameNode {
    private final GameScene scene;
    private final Color bgColor = new Color(255,255,255,100);
    
    //1~7
    private int page = 1;

    public PauseNode(GameScene scene) {
        this.scene = scene;
        BGMPlayer.getInstance().stop();
    }
    
    @Override
    protected void drawNode(NodeGraphics g) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void listenKeys(boolean[] keys) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void listenMouse(MouseInfo info) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void updateNode() {
        // TODO Auto-generated method stub
        
    }

}
