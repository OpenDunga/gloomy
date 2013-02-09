package ggjsap2013.models.score;

import ggjsap2013.models.Stage;

/**
 * スコアのモデル.
 * @author tohhy
 */
public class Score {
    private final Stage stage;
    //スコア
    private int score = 0;
    /*
     * 獲得したキャラ数
     * lengthとは別、列を消費してもこの数値は減らない
     * この数値が一定以上になるとレベルが上昇する
     */
    private int charaCount = 0;
    
    public Score(Stage stage) {
        this.stage = stage;
    }
    
    public void addScore(int toAdd) {
        this.score += toAdd;
    }
    
    public void charaCountUp() {
        charaCount++;
        
        if (stage.getCurrentLevel().getLevelClearCount() == charaCount) {
            stage.nextLevel();
        }
        
    }
    
    public void applyBonus() {
        score += charaCount*1000;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getCharaCount() {
        return charaCount;
    }

    public void setCharaCount(int charaCount) {
        this.charaCount = charaCount;
    }
}
