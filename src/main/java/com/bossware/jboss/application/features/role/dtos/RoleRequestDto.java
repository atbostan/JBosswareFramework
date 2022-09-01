package com.bossware.jboss.application.features.role.dtos;

import com.bossware.jboss.domain.entities.User;

import java.util.Objects;

public class RoleRequestDto {

    private  String roleName;
    private long userId;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleRequestDto that = (RoleRequestDto) o;
        return userId == that.userId && roleName.equals(that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName, userId);
    }

    @Override
    public String toString() {
        return "RoleRequestDto{" +
                "roleName='" + roleName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
