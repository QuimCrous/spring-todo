package springtodo.springtodo.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class TodoDTO {
    @NotEmpty
    @Size(min = 1, max = 200)
    private String title;
    private boolean isCompleted;

    private Long userId;


    public TodoDTO(String title, boolean isCompleted, Long userId) {
        this.title = title;
        this.isCompleted = isCompleted;
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
