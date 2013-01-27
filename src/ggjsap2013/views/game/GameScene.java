package ggjsap2013.views.game;

import java.awt.event.KeyEvent;

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
    private StageNode stageNode;
    private final PauseNode pauseNode;
    private boolean isGamePaused = false;
    
    public GameScene() {
        reset();
        this.pauseNode = new PauseNode(this);
    }
    
    public void reset() {
        removeAllChild();
        this.pause(false);
        stageNode = new StageNode(this);
        this.stage = stageNode.getModel();
        this.add(stageNode);
        InfomationNode score = new InfomationNode(stageNode.getModel());
        this.add(score);
        BGMPlayer.getInstance().setMedia("ggjsap2013/resources/sounds/ggj1-1.wav");
        BGMPlayer.getInstance().play();
    }
    
    @Override
    protected void updateNode() {
        if(stage.isGameOver()) {
            gameOver();
        }
        if(stage.isPaused()) {
            if(!isGamePaused)
                gamePause(true);
        }
    }
    
    public void gamePause(boolean isPaused) {
        isGamePaused = isPaused;
        //このノードはポーズする
        pause(isPaused);
        stageNode.remove(pauseNode);
        if(isPaused) {
            BGMPlayer.getInstance().setVolume(0.5);
            stageNode.add(pauseNode);
        } else {
            BGMPlayer.getInstance().setVolume(1.0);
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
