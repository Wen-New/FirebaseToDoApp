package sg.edu.rp.webservices.firebasetodoapp;

import java.io.Serializable;

public class ToDoItem implements Serializable {

    private String title;
    private String date;
    private int days;
    private boolean complete;

    public ToDoItem() {
        // Default constructor required for calls to DataSnapshot.getValue(Message.class)

    }

    public ToDoItem(String title, String date, int days, boolean complete) {
        this.title = title;
        this.date = date;
        this.days = days;
        this.complete = complete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @Override
    public String toString() {
        return "ToDoItem{" +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", days='" + days + '\'' +
                ", complete='" + complete + '\'' +
                '}';
    }
}

