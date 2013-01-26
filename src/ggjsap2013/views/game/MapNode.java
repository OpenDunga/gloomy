package ggjsap2013.views.game;

import ggjsap2013.Gloomy;
import ggjsap2013.models.Stage;
import ggjsap2013.models.map.Block;
import ggjsap2013.models.map.MapModel;
import ggjsap2013.models.map.barricades.Barricade;
import ggjsap2013.models.map.barricades.Wall;
import ggjsap2013.models.map.item.CharacterItem;
import ggjsap2013.models.map.item.PointItem;

import java.awt.Color;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.images.GameImage;
import jp.tohhy.gamepanel.images.Images;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class MapNode extends GameNode {
    private final Stage stage;
    private final MapModel map;
    
    public MapNode(Stage stage) {
        this.stage = stage;
        this.map = stage.getMap();
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
                	if (b instanceof PointItem) {
                	    PointItem pointItem = (PointItem) b;
                        g.setColor(Color.BLACK);
                        g.drawGameImage(getPointItemImage(pointItem.getType()), j*size, i*size);
                        
                	} else if (b instanceof CharacterItem) {
                        g.setColor(Color.BLUE);
                        g.drawText(b.toString(), j*size, i*size);
                		
                	} else if (b instanceof Barricade) {
                        g.setColor(Color.red);
                        g.drawText(b.toString(), j*size, i*size);
                	} else if (b instanceof Wall) {
                	    g.setColor(Color.lightGray);
                        g.fillRect(j*size, i*size, Gloomy.CHIP_SIZE, Gloomy.CHIP_SIZE);
                	}
                }
            }
        }
    }
    
    private GameImage getPointItemImage(PointItem.TYPES type) {
        switch(type) {
        case CANDY:
            return Images.get("item_1");
        case DONUT:
            return Images.get("item_2");
        case RIBON:
            return Images.get("item_3");
        case CAKE:
            return Images.get("item_4");
        case JEWEL:
            return Images.get("item_5");
        case TRUMPET:
            return Images.get("item_6");
        }
        return null;
    }

    @Override
    protected void listenKeys(boolean[] arg0) {}

    @Override
    protected void listenMouse(MouseInfo arg0) {}

    @Override
    protected void updateNode()
    {
        for(int i=0; i<map.getArray().length; i++) {
            for(int j=0; j<map.getArray()[i].length; j++) {
            	Block b = map.getArray()[i][j];
            	if (b != null) {
                    
            		b.setCurrentMove(b.getCurrentMove() + 1);
            		
                    if(b.getMoveWait() <= b.getCurrentMove()) {
                        b.setCurrentMove(0);
                        b.move(map, j, i);
                    }

            	}
            }
        }
    }
}
