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
        reset();
    }
    
    public void reset() {
        removeAllChild();
        StageNode stage = new StageNode(this);
        this.add(stage);
        InfomationNode score = new InfomationNode(stage.getStage());
        this.add(score);
    }
    
    public void gameOver() {
        this.add(new GameOverNode(this));
    }
    @Override
    protected void drawNode(NodeGraphics g) {}

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}
}
