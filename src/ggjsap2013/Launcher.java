package ggjsap2013;

import ggjsap2013.utils.ImageLoader;
import ggjsap2013.views.game.GameScene;

import java.awt.Rectangle;
import java.io.IOException;

import jp.tohhy.gamepanel.launchers.GameWindow;

/**
 * 起動用ランチャー.
 * @author tohhy
 */
public class Launcher {
    
    public static void main(String[] args) {
        loadImages();
        new GameWindow("SnakeGame", new Rectangle(100, 100, 800, 600), new GameScene()); 
    }
    
    /**
     * 各種画像のロード.
     */
    private static void loadImages() {
        try {
            ImageLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
