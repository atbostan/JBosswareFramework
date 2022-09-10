package com.bossware.jboss.domain.entities;

import com.bossware.jboss.domain.entities.base.JBossEntityBase;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "roles")
public class Role extends JBossEntityBase {

    @Column(length = 25,nullable = false)
    private String roleName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User User) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Role role = (Role) o;
        return roleName.equals(role.roleName) && user.equals(role.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName, user);
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleName='" + roleName + '\'' +
                ", user=" + user +
                ", id=" + id +
                ", creationTime=" + creationTime +
                ", creatorUserId=" + creatorUserId +
                ", modificationTime=" + modificationTime +
                ", modifierUserId=" + modifierUserId +
                '}';
    }
}
