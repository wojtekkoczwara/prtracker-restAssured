package pojo;

public class PostSnatch{

    private String scoreDate;
    private String exerciseName;
    private int prWeight;

    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getPrWeight() {
        return prWeight;
    }

    public void setPrWeight(int prWeight) {
        this.prWeight = prWeight;
    }

    public PostSnatch(String exerciseName, String scoreDate, int prWeight) {
        this.exerciseName = exerciseName;
        this.scoreDate = scoreDate;
        this.prWeight = prWeight;
    }

    public PostSnatch() {
    }
}
