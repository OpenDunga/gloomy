package ggjsap2013.utils;

import java.io.IOException;

import jp.tohhy.gamepanel.images.Images;

public class ImageLoader {
    private static String prefix = "ggjsap2013/resources/images/";
    
    public static void load() throws IOException {
        
        Images.put("logo", prefix + "logo.jpg");
        Images.put("game_back", prefix + "system/game_back.jpg");
        Images.put("field_back", prefix + "system/field_back.jpg");
        

        loadCharaChips();
        
        loadAnimalCharaChips();
        
        loadItemChips();
        
        loadEffectChips();
        
        loadBarricadeChips();
    }
    
    private static void loadItemChips() throws IOException {
        for(int i=1; i<=6; i++) {
            Images.put("item_" + i, prefix + "items/item_" + i + ".png");
        }
        
    }
    
    private static void loadCharaChips() throws IOException {
        loadCharaChip(1, "alice");
        loadCharaChip(2, "marm");
        loadCharaChip(3, "akaz");
        loadCharaChip(4, "sira");
        loadCharaChip(5, "tinkerbell");
    }
    private static void loadCharaChip(int id, String name) throws IOException {
        Images.put("chara_" + id + "_n", prefix + "charachips/" + name + "_n.png");
        Images.put("chara_" + id + "_s", prefix + "charachips/" + name + "_s.png");
        Images.put("chara_" + id + "_e", prefix + "charachips/" + name + "_e.png");
        Images.put("chara_" + id + "_w", prefix + "charachips/" + name + "_w.png");
    }
    
    private static void loadAnimalCharaChips() throws IOException {
        loadAnimalCharaChip(1, "ahiru");
        loadAnimalCharaChip(2, "kuma");
        loadAnimalCharaChip(3, "lion");
        loadAnimalCharaChip(4, "panda");
        loadAnimalCharaChip(5, "usagi");
    }
    
    private static void loadAnimalCharaChip(int id, String name) throws IOException {
        Images.put("animal_" + id + "_n", prefix + "animals/" + name + "_n.png");
        Images.put("animal_" + id + "_s", prefix + "animals/" + name + "_s.png");
        Images.put("animal_" + id + "_e", prefix + "animals/" + name + "_e.png");
        Images.put("animal_" + id + "_w", prefix + "animals/" + name + "_w.png");
    }
    
    private static void loadEffectChips() throws IOException
    {
    	loadEffetcChip("white");
    	loadEffetcChip("blue");
    	loadEffetcChip("green");
    	loadEffetcChip("red");
    	loadEffetcChip("yellow");
    }
    
    private static void loadEffetcChip(String name) throws IOException
    {
        Images.put(getEffectImageKey(name, 0), prefix + "effects/effect_" + name + "_0.png");
        Images.put(getEffectImageKey(name, 1), prefix + "effects/effect_" + name + "_1.png");
        Images.put(getEffectImageKey(name, 2), prefix + "effects/effect_" + name + "_2.png");
    }

    
    public static String getEffectImageKey(String name, int index)
    {
    	return "effect_" + name + "_" + index;
    }
    
    
    private static void loadBarricadeChips() throws IOException
    {
    	loadBarricadeChip("stone");
    	loadBarricadeChip("rock");
    	loadBarricadeChip("bomb");
    	loadBarricadeChip("toy_s");
    	loadBarricadeChip("toy_n");
    	loadBarricadeChip("toy_w");
    	loadBarricadeChip("toy_e");
    	loadBarricadeChip("king_s");
    	loadBarricadeChip("king_n");
    	loadBarricadeChip("king_w");
    	loadBarricadeChip("king_e");
    }
    
    private static void loadBarricadeChip(String name) throws IOException
    {
        Images.put(getBarricadeImageKey(name), prefix + "barricades/" + name + ".png");
    }

    
    public static String getBarricadeImageKey(String name)
    {
    	return "barricade_" + name;
    }
}
