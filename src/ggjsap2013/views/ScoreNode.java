package ggjsap2013.views;

import ggjsap2013.models.Stage;
import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

/**
 * スコア表示用のノードです
 * 
 * @author Casamorica
 *
 */
public class ScoreNode extends GameNode
{
	
	private final Stage stage;
	
	/**
	 * インスタンス作るよ
	 */
	public ScoreNode(Stage stage)
	{
		this.stage = stage;
	}

	@Override
	protected void drawNode(NodeGraphics g)
	{
		int scoreValue = stage.getScore().getScore();
		
		g.drawText("" + scoreValue, 10, 10);
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
