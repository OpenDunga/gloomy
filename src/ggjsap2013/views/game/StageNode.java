package ggjsap2013.views.game;

import ggjsap2013.models.Stage;
import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * サンプルのステージ.
 * @author tohhy
 */
public class StageNode extends GameNode {
    private final GameScene scene;
    private final Stage stage = new Stage();
    private boolean isEnd = false;
    
    public StageNode(GameScene scene) {
        this.scene = scene;
        this.add(new MapNode(getStage()));
        this.add(new SnakeNode(getStage()));
        this.setPosition(230, 20);
    }

    @Override
    protected void drawNode(NodeGraphics g) {}

    @Override
    protected void updateNode() {
        if(isEnd) return;
        if(getStage().isGameOver()) {
            scene.gameOver();
            isEnd = true;
        }
    }

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

    public Stage getStage() {
        return stage;
    }
}
