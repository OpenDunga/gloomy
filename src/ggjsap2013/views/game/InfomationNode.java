package ggjsap2013.views.game;

import ggjsap2013.models.Stage;
import ggjsap2013.models.skill.Skill;
import ggjsap2013.models.snake.SnakeBody;

import java.awt.Color;
import java.awt.Font;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * スコアや長さの情報表示用のノードです
 * 
 * @author Casamorica
 *
 */
public class InfomationNode extends GameNode
{
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
	    g.drawText("SCORE", 15, 15);
	    g.drawText("" + scoreValue, 10, 40);
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
            g.drawText(body.getType().getName(), 15, 110);
            if(skill != null)
                g.drawText(skill.getType().getDescription(), 15, 150);
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
        
        g.drawText("length: " + lengthValue, 15, 460);
        g.drawText("charaCount: " + charaCountValue, 15, 490);
        g.drawText("level: " + levelValue, 15, 520);
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
