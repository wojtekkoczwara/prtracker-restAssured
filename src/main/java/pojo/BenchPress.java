package pojo;

public class BenchPress extends PRPojo {

    private String exerciseName;
    private int prWeight;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BenchPress(String scoreDate, int id, String exerciseName, int prWeight) {
        super(scoreDate);
        this.id = id;
        this.exerciseName = exerciseName;
        this.prWeight = prWeight;
    }

    public BenchPress() {
        this.exerciseName = "Bench press";
    }

    @Override
    public String toString() {
        return "BenchPress{" +
                "exerciseName='" + exerciseName + '\'' +
                ", prWeight=" + prWeight +
                ", id=" + id +
                '}';
    }
}
