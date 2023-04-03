package springtodo.springtodo.models;

import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "todo_user_id")
    private User todoUser;

    public Todo() {
    }

    public Todo(String title, boolean completed, User todoUser) {
        setTitle(title);
        setCompleted(completed);
        setTodoUser(todoUser);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public User getTodoUser() {
        return todoUser;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setTodoUser(User todoUser) {
        this.todoUser = todoUser;
    }
}
