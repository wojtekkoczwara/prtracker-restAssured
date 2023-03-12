package pojo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.beans.ConstructorProperties;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "exerciseName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GetSnatch.class, name="snatch"),
        @JsonSubTypes.Type(value = CleanJerk.class, name="clean jerk"),
        @JsonSubTypes.Type(value = BenchPress.class, name="bench press"),
        @JsonSubTypes.Type(value = Run5.class, name="run 5"),
})
public abstract class PRPojo {

    private String scoreDate;

    public String getScoreDate() {
        return scoreDate;
    }

    public void setScoreDate(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    public PRPojo(String scoreDate) {
        this.scoreDate = scoreDate;
    }

    @ConstructorProperties("{exerciseName}")
    public PRPojo() {
    }
}
