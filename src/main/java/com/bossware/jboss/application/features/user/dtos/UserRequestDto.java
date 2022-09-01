package com.bossware.jboss.application.features.user.dtos;

import java.util.Objects;

public class UserRequestDto {

    private String name;
    private String lastName;
    private String userName;
    private String password;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestDto that = (UserRequestDto) o;
        return name.equals(that.name) && lastName.equals(that.lastName) && userName.equals(that.userName) && password.equals(that.password) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, userName, password, email);
    }

    @Override
    public String toString() {
        return "UserRequestDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
