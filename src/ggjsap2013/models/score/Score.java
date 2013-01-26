package ggjsap2013.models.score;

/**
 * スコアのモデル.
 * @author tohhy
 */
public class Score {
    //スコア
    private int score = 0;
    /*
     * 獲得したキャラ数
     * lengthとは別、列を消費してもこの数値は減らない
     * この数値が一定以上になるとレベルが上昇する
     */
    private int charaCount = 0;
    
    public void addScore(int toAdd) {
        this.score += toAdd;
    }
    
    public void charaCountUp() {
        charaCount++;
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
