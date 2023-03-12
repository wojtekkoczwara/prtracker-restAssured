package pojo;

public class Run5 extends PRPojo {

    private String exerciseName;

    private int id;
    private int runningTimeInSeconds;
    private int secPerKilometer;
    private int distanceInKm;

    public int getRunningTimeInSeconds() {
        return runningTimeInSeconds;
    }

    public void setRunningTimeInSeconds(int runningTimeInSeconds) {
        this.runningTimeInSeconds = runningTimeInSeconds;
    }

    public int getSecPerKilometer() {
        return secPerKilometer;
    }

    public void setSecPerKilometer(int secPerKilometer) {
        this.secPerKilometer = secPerKilometer;
    }

    public int getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(int distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

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

    public Run5(String scoreDate, String exerciseName, int id, int runningTimeInSeconds, int secPerKilometer
            , int distanceInKm) {
        super(scoreDate);
        this.exerciseName = exerciseName;
        this.id = id;
        this.runningTimeInSeconds = runningTimeInSeconds;
        this.secPerKilometer = secPerKilometer;
        this.distanceInKm = distanceInKm;
    }

    public Run5() {
        this.exerciseName = "run 5";
    }

    @Override
    public String toString() {
        return "Run5{" +
                "exerciseName='" + exerciseName + '\'' +
                ", id=" + id +
                ", runningTimeInSeconds=" + runningTimeInSeconds +
                ", secPerKilometer=" + secPerKilometer +
                ", distanceInKm=" + distanceInKm +
                '}';
    }
}
