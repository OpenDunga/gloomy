package ggjsap2013.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;

import jp.tohhy.gamepanel.images.ImageBuilder;
import jp.tohhy.gamepanel.images.Images;

public class ImageLoader {
    private static String prefix = "ggjsap2013/resources/images/";
    
    public static void load() throws IOException {
        
        //通常の画像読み込み
        Images.put("logo", prefix + "logo.jpg");
        
        //チップセットの読み込み
        //画像を32*32のチップに分割して左上から順に配列に格納している
        BufferedImage[] bottomMap =
                ImageBuilder.create(prefix + "bottommap.png").divideBySize(32, 32);
        Images.put("bottommap", bottomMap);
        
        //キャラチップ
        Images.put("chara_1_n", prefix + "charachips/alice_n.png");
        Images.put("chara_1_s", prefix + "charachips/alice_s.png");
        Images.put("chara_1_e", prefix + "charachips/alice_e.png");
        Images.put("chara_1_w", prefix + "charachips/alice_w.png");
        
        loadAnimalCharaChips();
    }
    
    private static void loadAnimalCharaChips() throws IOException {
        loadAnimalCharaChip(1, "ahiru");
        loadAnimalCharaChip(2, "kuma");
        loadAnimalCharaChip(3, "lion");
        loadAnimalCharaChip(4, "panda");
        loadAnimalCharaChip(5, "usagi");
    }
    
    private static void loadAnimalCharaChip(int id, String name) throws IOException {
        Images.put("animal_" + id + "_n", prefix + "animals/" + name + "_s.png");
        Images.put("animal_" + id + "_s", prefix + "animals/" + name + "_s.png");
        Images.put("animal_" + id + "_e", prefix + "animals/" + name + "_s.png");
        Images.put("animal_" + id + "_w", prefix + "animals/" + name + "_s.png");
    }

}
