package com.bossware.jboss.domain.entities;

import com.bossware.jboss.domain.entities.base.JBossEntityBase;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

@Entity(name = "users")
public class User extends JBossEntityBase {

    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 50)
    private String lastName;

    @Column(nullable = false,length = 25)
    private String userName;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 120 )
    private String email;
    private Date lastLoginDate;
    private Date lastLoginDateDisplay;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean isActive;

    @Column(nullable = false)
    @ColumnDefault("true")
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
        if (!super.equals(o)) return false;
        User User = (User) o;
        return isActive == User.isActive && isNotLocked == User.isNotLocked && name.equals(User.name) && lastName.equals(User.lastName) && userName.equals(User.userName) && password.equals(User.password) && email.equals(User.email) && Objects.equals(lastLoginDate, User.lastLoginDate) && Objects.equals(lastLoginDateDisplay, User.lastLoginDateDisplay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, lastName, userName, password, email, lastLoginDate, lastLoginDateDisplay, isActive, isNotLocked);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", lastLoginDateDisplay=" + lastLoginDateDisplay +
                ", isActive=" + isActive +
                ", isNotLocked=" + isNotLocked +
                ", id=" + id +
                ", creationTime=" + creationTime +
                ", creatorUserId=" + creatorUserId +
                ", modificationTime=" + modificationTime +
                ", modifierUserId=" + modifierUserId +
                '}';
    }
}
