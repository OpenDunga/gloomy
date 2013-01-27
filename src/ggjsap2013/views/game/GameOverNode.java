package ggjsap2013.views.game;

import ggjsap2013.models.score.RankingModel;
import ggjsap2013.models.score.Record;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.images.Images;
import jp.tohhy.gamepanel.sounds.BGMPlayer;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class GameOverNode extends GameNode {
    private final GameScene scene;
    private final Color bgColor = new Color(255,255,255,100);
    private final NameInputNode input = new NameInputNode();
    private final RankingModel ranking = RankingModel.getInstance();
    
    public GameOverNode(GameScene scene) {
        this.scene = scene;
        BGMPlayer.getInstance().stop();
        //デバッグ中
        this.add(input);
    }

    @Override
    protected void drawNode(NodeGraphics g) {
        g.drawGameImage(Images.get("game_back"));
        g.setColor(bgColor);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.white);
        g.fillRect(200, 80, 400, 350);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        g.drawText("Game Over", 290, 20);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.drawText("Enter your name: ", 200, 500);
        g.drawText("Press SPACE to restart.", 290, 550);
    }

    @Override
    protected void listenKeys(boolean[] keys) {
        if(keys[KeyEvent.VK_SPACE]) {
            scene.reset();
            ranking.addRecord(new Record(input.getName(), scene.getStage().getScore().getScore()));
            ranking.save();
        }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}
}
