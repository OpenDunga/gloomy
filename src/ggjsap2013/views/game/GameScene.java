package ggjsap2013.views.game;

import ggjsap2013.models.Stage;
import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.images.Images;
import jp.tohhy.gamepanel.sounds.BGMPlayer;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * ゲーム画面全体.
 * @author tohhy
 */
public class GameScene extends GameNode {
    private Stage stage;
    
    public GameScene() {
        reset();
    }
    
    public void reset() {
        removeAllChild();
        this.pause(false);
        StageNode stage = new StageNode(this);
        this.stage = stage.getStage();
        this.add(stage);
        InfomationNode score = new InfomationNode(stage.getStage());
        this.add(score);
        BGMPlayer.getInstance().setMedia("ggjsap2013/resources/sounds/ggj1-1.wav");
        BGMPlayer.getInstance().play();
    }
    
    @Override
    protected void updateNode() {
        if(stage.isGameOver()) {
            gameOver();
        }
    }
    
    public void gameOver() {
        this.add(new GameOverNode(this));
        this.pause(true);
    }
    
    @Override
    protected void drawNode(NodeGraphics g) {
        g.drawGameImage(Images.get("game_back"));
    }

    @Override
    protected void listenKeys(boolean[] keys) {}

    @Override
    protected void listenMouse(MouseInfo info) {}


}
