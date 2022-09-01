package com.bossware.jboss.application.features.role.dtos;

import java.util.Objects;

public class RoleResponseDto {

    private long id;
    private String roleName;
    private long userId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
        RoleResponseDto that = (RoleResponseDto) o;
        return id == that.id && userId == that.userId && roleName.equals(that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, userId);
    }

    @Override
    public String toString() {
        return "RoleResponseDto{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", userId=" + userId +
                '}';
    }
}
