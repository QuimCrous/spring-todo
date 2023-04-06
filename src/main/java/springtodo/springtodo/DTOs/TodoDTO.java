package springtodo.springtodo.DTOs;

public class TodoDTO {

    private String title;
    private boolean isCompleted;

    private String userName;


    public TodoDTO(String title, boolean isCompleted, String userName) {
        this.title = title;
        this.isCompleted = isCompleted;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
