package com.example.universityeventstools.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class ServicePerson {

    @SerializedName("department")
    private String department;

    @SerializedName("firstname")
    private String firstname;

    @SerializedName("groupID")
    private int groupID;

    @SerializedName("lastname")
    private String lastname;

    @SerializedName("password")
    private String password;

    @SerializedName("personID")
    private String personID;

    @SerializedName("role")
    private String role;

    public ServicePerson() {

    }

    public ServicePerson(String department, String firstname, int groupID, String lastname, String password, String personID, String role) {
        this.department = department;
        this.firstname = firstname;
        this.groupID = groupID;
        this.lastname = lastname;
        this.password = password;
        this.personID = personID;
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicePerson that = (ServicePerson) o;

        if (groupID != that.groupID) return false;
        if (department != null ? !department.equals(that.department) : that.department != null) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (personID != null ? !personID.equals(that.personID) : that.personID != null) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = department != null ? department.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + groupID;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (personID != null ? personID.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServicePerson{" +
                "department='" + department + '\'' +
                ", firstname='" + firstname + '\'' +
                ", groupID=" + groupID +
                ", lastname='" + lastname + '\'' +
                ", password='" + password + '\'' +
                ", personID='" + personID + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
