package ggjsap2013.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import jp.tohhy.gamepanel.images.ImageBuilder;
import jp.tohhy.gamepanel.images.Images;

public class ImageLoader {
    
    public static void load() throws IOException {
        //通常の画像読み込み
        Images.put("splash", "ggjsap2013/resources/images/kawaz.jpg");
        
        //チップセットの読み込み
        //画像を32*32のチップに分割して左上から順に配列に格納している
        BufferedImage[] bottomMap =
                ImageBuilder.create("ggjsap2013/resources/images/bottommap.png").divideBySize(32, 32);
        Images.put("bottommap", bottomMap);
    }

}
