package ggjsap2013.views.game;

import ggjsap2013.models.Stage;
import ggjsap2013.models.skill.Skill;
import ggjsap2013.models.snake.SnakeBody;
import ggjsap2013.models.snake.SnakeModel.Direction;

import java.awt.Color;
import java.awt.Font;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.images.GameImage;
import jp.tohhy.gamepanel.images.Images;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * スコアや長さの情報表示用のノードです
 * 
 * @author Casamorica
 *
 */
public class InfomationNode extends GameNode
{
	private final Font	defultFont = new Font(Font.SANS_SERIF, Font.PLAIN, 18);
	private final Font	boldFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
	
    private final Color bgColor = new Color(255, 255, 255, 150);
	private final Stage stage;
	
	/**
	 * インスタンス作るよ
	 */
	public InfomationNode(Stage stage)
	{
		this.stage = stage;
	}

	@Override
	protected void drawNode(NodeGraphics g)
	{
	    drawAreaBackGround(g);
		g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		drawScore(g);
		drawHeadInfo(g);
		drawSnakeInfo(g);
	}
	
	private void drawAreaBackGround(NodeGraphics g) 
	{
	    g.setColor(bgColor);
	    g.fillRect(10, 10, 210, 90);
	    g.fillRect(10, 110, 210, 335);
	    g.fillRect(10, 455, 210, 135);
	}
	
	/**
	 * スコア表示（仕様書のゲーム動作画面①の内容）を描画
	 * @param g
	 */
	private void drawScore(NodeGraphics g) 
	{
	    int scoreValue = stage.getScore().getScore();
        g.setFont(boldFont);
	    g.drawText("SCORE", 35, 15);
        g.setFont(defultFont);
        
	    g.drawText(String.format("%9d", scoreValue),120, 55);
	}
	
	/**
     * スコア表示（仕様書のゲーム動作画面②の内容）を描画
     * @param g
     */
    private void drawHeadInfo(NodeGraphics g) 
    {
        SnakeBody body = getCurrentBody();
        if(body != null) {
            Skill skill = body.getSkill();
//            g.drawText(body.getType().getName(), 15, 110);
            
            GameImage image = Images.get(SnakeNode.createImageName(body, Direction.SOUTH));
            
            
            g.drawGameImage(image, 20, 120);
            g.setFont(boldFont);
            g.drawText(body.getType().getName(), 70, 130);
            
            g.setFont(boldFont);
            g.drawText("スキル", 20, 200);
            g.setFont(defultFont);
            
            if(skill != null) {
                g.drawText(skill.getType().getDescription(),50, 240);
            }
        }
    }
    
    private SnakeBody getCurrentBody() {
        return stage.getSnake().getBodies().getHead();
    }
    
    /**
     * スコア表示（仕様書のゲーム動作画面③の内容）を描画
     * @param g
     */
    private void drawSnakeInfo(NodeGraphics g) 
    {
        int lengthValue = stage.getSnake().getBodies().size();
        int charaCountValue = stage.getScore().getCharaCount();
        int levelValue = stage.getCurrentLevelNum();
        int levelUpValue = stage.getCurrentLevel().getLevelClearCount();
        
        g.drawText("length: " + lengthValue, 25, 470);
        g.drawText("charaCount: " + charaCountValue + "/" + levelUpValue, 25, 500);
        g.drawText("level: " + (levelValue+1), 25, 530);
    }

	@Override
	protected void listenKeys(boolean[] keys)
	{
	}

	@Override
	protected void listenMouse(MouseInfo info)
	{
	}

	@Override
	protected void updateNode()
	{
	}
}
