package springtodo.springtodo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import springtodo.springtodo.embedables.Address;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String name;
    @Column(unique = true)
    private String userName;
    private String password;
    @Embedded
    private Address userAddress;

    @OneToMany(mappedBy = "todoUser")
    @JsonIgnore
    List<Todo> todos;

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public User(String name, String userName, String password, Address userAddress) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userAddress = userAddress;
    }

    public User() {
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public Address getUserAddress() {
        return userAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserAddress(Address userAddress) {
        this.userAddress = userAddress;
    }
}
