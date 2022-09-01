package com.bossware.jboss.application.features.authority.dtos;

import java.util.Objects;

public class AuthResponseDto {

    private long id;
    private String authName;
    private long roleId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthResponseDto that = (AuthResponseDto) o;
        return id == that.id && roleId == that.roleId && authName.equals(that.authName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authName, roleId);
    }

    @Override
    public String toString() {
        return "AuthResponseDto{" +
                "id=" + id +
                ", authName='" + authName + '\'' +
                ", userId=" + roleId +
                '}';
    }
}
