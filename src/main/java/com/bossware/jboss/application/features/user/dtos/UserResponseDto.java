package com.bossware.jboss.application.features.user.dtos;

import com.bossware.jboss.domain.entities.User;

import java.util.Date;
import java.util.Objects;

public class UserResponseDto {

    private String name;
    private String lastName;
    private String userName;

    private String email;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;
    private boolean isActive;
    private boolean isNotLocked;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponseDto that = (UserResponseDto) o;
        return isActive == that.isActive && isNotLocked == that.isNotLocked && name.equals(that.name) && lastName.equals(that.lastName) && userName.equals(that.userName) && email.equals(that.email) && Objects.equals(lastLoginDate, that.lastLoginDate) && Objects.equals(lastLoginDateDisplay, that.lastLoginDateDisplay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, userName, email, lastLoginDate, lastLoginDateDisplay, isActive, isNotLocked);
    }

    @Override
    public String toString() {
        return "UserResponseDto{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLoginDateDisplay=" + lastLoginDateDisplay +
                ", isActive=" + isActive +
                ", isNotLocked=" + isNotLocked +
                '}';
    }
}
