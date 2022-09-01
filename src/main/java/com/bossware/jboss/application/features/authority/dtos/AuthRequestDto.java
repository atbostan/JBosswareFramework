package com.bossware.jboss.application.features.authority.dtos;

import java.util.Objects;

public class AuthRequestDto {
    private String authName;
    private long roleId;

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
        AuthRequestDto that = (AuthRequestDto) o;
        return roleId == that.roleId && authName.equals(that.authName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authName, roleId);
    }

    @Override
    public String toString() {
        return "AuthRequestDto{" +
                "authName='" + authName + '\'' +
                ", userId=" + roleId +
                '}';
    }
}
