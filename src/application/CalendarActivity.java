package application;
import java.time.ZonedDateTime;

public class CalendarActivity {
    private ZonedDateTime date;
    private String taskTitle;
    private String taskTime;

    public CalendarActivity(ZonedDateTime date, String taskTitle, String taskTime) {
        this.date = date;
        this.taskTitle = taskTitle;
        this.taskTime = taskTime;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }
    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }
    
    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                ", taskTitle='" + taskTitle + 
                ", taskTime='" + taskTime + 
                '}';
    }
}
