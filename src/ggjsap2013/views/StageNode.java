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
    
    public StageNode() {

        Stage model = new Stage();
        this.add(new MapNode(model.getMap()));
        this.add(new SnakeNode(model.getSnake()));
        this.setPosition(230, 20);
    }

    @Override
    protected void drawNode(NodeGraphics g) {}

    @Override
    protected void updateNode() {}

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

}
