package ggjsap2013.models.score;

import ggjsap2013.utils.SerializationUtils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * シリアライズして外部にscore.serとして保存
 * @author tohhy
 */
public class RankingModel {
    private static RankingModel instance;
    private final ArrayList<Record> list;
    
    private RankingModel(ArrayList<Record> list) {
        this.list = list;
    }
    
    public Record getRecord(int index) {return list.get(index);}
    
    public static RankingModel getInstance() {
        if(instance == null) {
            if(SerializationUtils.isSaveDataExists()) {
                instance = new RankingModel(SerializationUtils.loadRanking());
            } else {
                instance = new RankingModel(new ArrayList<Record>());
            }
        }
        return instance;
    }
    
    public int getRows() {
        return list.size();
    }
    
    public void addRecord(Record record) {
        list.add(record);
        Collections.sort(list);
    }
    
    public void save() {
        SerializationUtils.saveRanking(list);
    }
}
