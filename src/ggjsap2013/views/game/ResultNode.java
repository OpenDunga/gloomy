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

public class ResultNode extends GameNode {
    private final GameScene scene;
    private final Color bgColor = new Color(255,255,255,100);
    private final NameInputNode input = new NameInputNode();
    private final RankingModel ranking = RankingModel.getInstance();
    
    public ResultNode(GameScene scene) {
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
        drawRanking(g);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
        g.drawText("Game Over", 290, 20);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        g.drawText("Your score: " + scene.getStage().getScore().getScore(), 200, 450);
        g.drawText("Enter your name: ", 200, 500);
        g.drawText("Press SPACE to restart.", 290, 550);
    }
    
    private void drawRanking(NodeGraphics g) {
        int x = 200;
        int y = 80;
        g.setColor(Color.darkGray);
        g.fillRect(x, y, 400, 350);
        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        int rows = 8;
        if(ranking.getRows() < rows) rows = ranking.getRows();
        for(int i=0; i < rows; i++) {
            int drawY = y+20 + (i * 40);
            g.drawText("" + (i+1) + ". ", x+20, drawY);
            g.drawText(ranking.getRecord(i).getName(), x+50, drawY);
            g.drawText("" + ranking.getRecord(i).getScore(), x+130, drawY);
        }
    }

    @Override
    protected void listenKeys(boolean[] keys) {
        if(keys[KeyEvent.VK_SPACE]) {
            ranking.addRecord(new Record(input.getName(), scene.getStage().getScore().getScore()));
            scene.reset();
            ranking.save();
        }
    }

    @Override
    protected void listenMouse(MouseInfo info) {}

    @Override
    protected void updateNode() {}
}
