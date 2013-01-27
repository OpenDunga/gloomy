package ggjsap2013.views.game;

import java.awt.Color;
import java.awt.Font;

import ggjsap2013.models.Stage;
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
		
		g.setColor(Color.black);
		drawScore(g);
		drawSnakeInfo(g);
	}
	
	private void drawAreaBackGround(NodeGraphics g) 
	{
	    g.setColor(bgColor);
	    g.drawRect(0, 0, 0, 0);
	    g.drawRect(0, 0, 0, 0);
	    g.drawRect(0, 0, 0, 0);
	}
	
	/**
	 * スコア表示（仕様書のゲーム動作画面①の内容）を描画
	 * @param g
	 */
	private void drawScore(NodeGraphics g) 
	{
	    g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
	    int scoreValue = stage.getScore().getScore();
	    g.drawText("score: " + scoreValue, 10, 10);
	}
	
	/**
     * スコア表示（仕様書のゲーム動作画面②の内容）を描画
     * @param g
     */
    private void drawHeadInfo(NodeGraphics g) 
    {
        
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
        
        g.drawText("length: " + lengthValue, 10, 40);
        g.drawText("charaCount: " + charaCountValue, 10, 70);
        g.drawText("level: " + levelValue, 10, 110);
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
