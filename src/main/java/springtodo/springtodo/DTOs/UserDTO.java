package springtodo.springtodo.DTOs;

import jakarta.validation.constraints.NotNull;
import springtodo.springtodo.embedables.Address;

public class UserDTO {
    @NotNull
    private String name;
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private Address address;

    public UserDTO(String name, String userName, String password, Address address) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
