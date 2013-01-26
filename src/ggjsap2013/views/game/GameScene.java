package ggjsap2013.views.game;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * ゲーム画面全体.
 * @author tohhy
 */
public class GameScene extends GameNode {
    
    public GameScene() {
        StageNode stage = new StageNode();
        this.add(stage);
        InfomationNode score = new InfomationNode(stage.getStage());
        this.add(score);
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
