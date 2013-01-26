package ggjsap2013.views.game;

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
		int scoreValue = stage.getScore().getScore();
		int lengthValue = stage.getSnake().getBodies().size();
		int charaCountValue = stage.getScore().getCharaCount();
		g.drawText("score: " + scoreValue, 10, 10);
		g.drawText("length: " + lengthValue, 10, 40);
		g.drawText("charaCount: " + charaCountValue, 10, 70);
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
