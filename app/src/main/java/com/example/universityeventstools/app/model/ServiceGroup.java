package com.example.universityeventstools.app.model;

/**
 * Created by ykoby_000 on 17.05.2014.
 */
public class ServiceGroup {
    private String groupID;
    private String groupName;
    private String institute;

    public ServiceGroup(String groupID, String groupName, String institute) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.institute = institute;
    }

    public String getGroupID() {
        return this.groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getInstitute() {
        return this.institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceGroup that = (ServiceGroup) o;

        if (groupID != null ? !groupID.equals(that.groupID) : that.groupID != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;
        if (institute != null ? !institute.equals(that.institute) : that.institute != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupID != null ? groupID.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (institute != null ? institute.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceGroup{" +
                "groupID='" + groupID + '\'' +
                ", groupName='" + groupName + '\'' +
                ", institute='" + institute + '\'' +
                '}';
    }
}
