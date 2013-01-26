package ggjsap2013.models.score;

/**
 * スコアのモデル.
 * @author tohhy
 */
public class Score {
    private int score = 0;
    
    public void addScore(int toAdd) {
        this.score += toAdd;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
