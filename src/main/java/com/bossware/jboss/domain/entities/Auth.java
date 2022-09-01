package com.bossware.jboss.domain.entities;

import com.bossware.jboss.domain.entities.base.JBossEntityBase;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity(name = "authorities")
public class Auth extends JBossEntityBase {

    @Column(nullable = false,length = 25)
    private String authName;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Role role;

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Auth auth = (Auth) o;
        return authName.equals(auth.authName) && role.equals(auth.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), authName, role);
    }

    @Override
    public String toString() {
        return "Auth{" +
                "authName='" + authName + '\'' +
                ", role=" + role +
                '}';
    }
}
