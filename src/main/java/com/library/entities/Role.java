package com.library.entities;

public class Role {
    private long roleId;
    private String roleName;

    public Role(long id, String name) {
        this.roleId = id;
        this.roleName = name;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String toString() {
        return this.getRoleId() + "\t" + this.getRoleName();
    }
}
