package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.item.Item;
import ggjsap2013.models.map.item.Item.TYPES;

import java.awt.Color;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class MapNode extends GameNode {
    private final MapModel map;
    
    public MapNode(MapModel map) {
        this.map = map;
        map.getArray()[2][2] = new Item(TYPES.A);
        map.getArray()[6][3] = new Item(TYPES.B);
    }
    
    @Override
    protected void drawNode(NodeGraphics g) {
        int size = Gloomy.CHIP_SIZE;
        
        for(int i=0; i<map.getArray().length; i++) {
            for(int j=0; j<map.getArray()[i].length; j++) {
                g.setColor(Color.orange);
                g.drawRect(j*size, i*size, size, size);
                Block b = map.getArray()[i][j];
                if(b != null) {
                    g.setColor(Color.blue);
                    g.drawText(b.toString(), j*size, i*size);
                }
            }
        }
    }

    @Override
    protected void listenKeys(boolean[] arg0) {}

    @Override
    protected void listenMouse(MouseInfo arg0) {}

    @Override
    protected void updateNode() {}
}
