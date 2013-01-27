package ggjsap2013.views.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;

import jp.tohhy.gamepanel.GameNode;
import jp.tohhy.gamepanel.graphics.NodeGraphics;
import jp.tohhy.gamepanel.utils.MouseInfo;

public class NameInputNode  extends GameNode {
    private static final char[] ALPHABETS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    //0〜ALPHABETS.length-1、そのけたの文字
    private int[] name = new int[3];
    
    //0~2、けた位置
    private int focus = 0;
    
    public NameInputNode() {
        this.setKeyWait(10);
        this.setPosition(200,200);
    }
    
    @Override
    protected void drawNode(NodeGraphics g) {
        g.setColor(Color.blue);
        g.drawRect(0,0,60,25);
        g.setColor(Color.black);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 25));
        g.drawText("" + ALPHABETS[name[0]], 0, 0);
        g.drawText("" + ALPHABETS[name[1]], 20, 0);
        g.drawText("" + ALPHABETS[name[2]], 40, 0);
        g.setColor(Color.orange);
        g.drawRect(focus*20,0,20,25);
    }
    
    public String getName() {
        return "" + ALPHABETS[name[0]] + ALPHABETS[name[1]] + ALPHABETS[name[2]];
    }
    
    private void nextChar(int focus) {
        name[focus]++;
        if(name[focus] >= ALPHABETS.length) {
            name[focus] = 0;
        }
    }
    
    private void prevChar(int focus) {
        name[focus]--;
        if(name[focus] < 0) {
            name[focus] = ALPHABETS.length - 1;
        }
    }
    
    private void nextFocus() {
        focus++;
        if(focus >= name.length) focus = 0;
    }
    
    private void prevFocus() {
        focus--;
        if(focus < 0) focus = name.length-1;
    }
    
    @Override
    protected void listenKeys(boolean[] keys) {
        if(keys[KeyEvent.VK_UP]) {
            nextChar(focus);
            
        } else if(keys[KeyEvent.VK_DOWN]) {
            prevChar(focus);
        } else if(keys[KeyEvent.VK_LEFT]) {
            prevFocus();
        } else if(keys[KeyEvent.VK_RIGHT]) {
            nextFocus();
        }
    }
    
    @Override
    protected void listenMouse(MouseInfo arg0) {
        // TODO Auto-generated method stub
        
    }
    @Override
    protected void updateNode() {
        // TODO Auto-generated method stub
        
    }
    
    

}
