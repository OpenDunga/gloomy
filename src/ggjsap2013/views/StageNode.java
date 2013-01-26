package ggjsap2013.views;

import ggjsap2013.models.Stage;
import ggjsap2013.views.game.MapNode;
import ggjsap2013.views.game.SnakeNode;
import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * サンプルのステージ.
 * @author tohhy
 */
public class StageNode extends GameNode {
    private final Stage model = new Stage();
    private boolean isEnd = false;
    public StageNode() {
        this.add(new MapNode(model));
        this.add(new SnakeNode(model));
        this.setPosition(230, 20);
    }

    @Override
    protected void drawNode(NodeGraphics g) {}

    @Override
    protected void updateNode() {
        if(isEnd) return;
        if(model.isGameOver()) {
            this.add(new GameOverNode());
            isEnd = true;
        }
    }

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

}
