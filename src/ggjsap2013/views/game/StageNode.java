package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.Stage;

import java.awt.Color;

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
    private final Color bgColor = new Color(255, 255, 255, 150);
    
    public StageNode(GameScene scene) {
        this.scene = scene;
        this.add(new MapNode(getStage()));
        this.add(new SnakeNode(getStage()));
        this.setPosition(230, 20);
    }

    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(bgColor);
        g.fillRect(0, 0, 
                Gloomy.CHIP_SIZE * Gloomy.STAGE_WIDTH, 
                Gloomy.CHIP_SIZE * Gloomy.STAGE_HEIGHT);
    }

    @Override
    protected void updateNode() {}

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}

    public Stage getStage() {
        return stage;
    }
}
