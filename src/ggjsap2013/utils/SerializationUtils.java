package ggjsap2013.utils;

import ggjsap2013.models.score.Record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * ランキングデータ保持用のシリアライズ処理を行うためのユーティリティクラス.
 * @author tohhy
 */
public class SerializationUtils {
    /**
     * セーブデータのファイル.
     */
    private static final File SAVE_DATA = new File("score.ser");
    
    public static boolean isSaveDataExists() {
        return SAVE_DATA.exists();
    }
    
    public static void saveRanking(ArrayList<Record> ranking) {
        try {
            serialize(SAVE_DATA, ranking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Record> loadRanking() {
        try {
            return (ArrayList<Record>)deserialize(SAVE_DATA);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static void serialize(File file, Object object) throws IOException {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(oos != null)
                oos.close();
        }
    }
    
    private static Object deserialize(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            return ois.readObject();
        } finally {
            if(ois != null)
                ois.close();
        }
    }
}
