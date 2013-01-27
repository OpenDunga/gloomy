package ggjsap2013.models.score;

import java.io.Serializable;

public class Record implements Serializable, Comparable<Record> {
    private static final long serialVersionUID = 621161719172123759L;
    private final String name;
    private final int score;
    
    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Record o) {
        return this.score - o.getScore();
    }
    
    @Override
    public String toString() {
        return name + "　" + score;
    }
}
