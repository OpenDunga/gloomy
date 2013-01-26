package ggjsap2013.views;

import ggjsap2013.controllers.io.StageReader;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.snake.SnakeModel;
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
    	
    	StageReader stageReader = new StageReader();
    	MapModel map = stageReader.read(0); //TODO ステージインデックスちゃんとかえること！
    	
    	
//        MapModel map = new MapModel(14, 14);
        SnakeModel snake = new SnakeModel(map);
        this.add(new MapNode(map));
        this.add(new SnakeNode(snake));
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
