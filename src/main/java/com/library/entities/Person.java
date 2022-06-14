package com.library.entities;

public class Person {
    private long personId;
    private long roleId;
    private String name;
    private String lastname;
    private String email;
    private String phone;

    public Person(long personId, long roleId, String name, String lastname, String email, String phone) {
        this.personId = personId;
        this.roleId = roleId;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return this.getPersonId() + "\t" + this.getRoleId() + "\t" + this.getName() + "\t" + this.getLastname()
                + "\t" + this.getEmail() + "\t" + this.getPhone();
    }
}
