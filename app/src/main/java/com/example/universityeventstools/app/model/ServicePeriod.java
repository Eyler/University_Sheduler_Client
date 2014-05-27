
package com.example.universityeventstools.app.model;

import com.google.gson.annotations.SerializedName;

public class ServicePeriod{

    @SerializedName("auditorium")
   	private ServiceAuditorium serviceAuditorium;

    @SerializedName("day")
   	private String day;

    @SerializedName("discipline")
   	private String discipline;

    @SerializedName("groupID")
   	private String groupID;

    @SerializedName("lecturer")
   	private String lecturer;

    @SerializedName("periodID")
   	private String periodID;

    @SerializedName("periodNumber")
   	private String periodNumber;

    @SerializedName("periodType")
   	private String periodType;

    public ServicePeriod(ServiceAuditorium serviceAuditorium, String day, String discipline, String groupID, String lecturer, String periodID, String periodNumber, String periodType) {
        this.serviceAuditorium = serviceAuditorium;
        this.day = day;
        this.discipline = discipline;
        this.groupID = groupID;
        this.lecturer = lecturer;
        this.periodID = periodID;
        this.periodNumber = periodNumber;
        this.periodType = periodType;
    }

    public ServiceAuditorium getServiceAuditorium(){
		return this.serviceAuditorium;
	}
	public void setServiceAuditorium(ServiceAuditorium serviceAuditorium){
		this.serviceAuditorium = serviceAuditorium;
	}
 	public String getDay(){
		return this.day;
	}
	public void setDay(String day){
		this.day = day;
	}
 	public String getDiscipline(){
		return this.discipline;
	}
	public void setDiscipline(String discipline){
		this.discipline = discipline;
	}
 	public String getGroupID(){
		return this.groupID;
	}
	public void setGroupID(String groupID){
		this.groupID = groupID;
	}
 	public String getLecturer(){
		return this.lecturer;
	}
	public void setLecturer(String lecturer){
		this.lecturer = lecturer;
	}
 	public String getPeriodID(){
		return this.periodID;
	}
	public void setPeriodID(String periodID){
		this.periodID = periodID;
	}
 	public String getPeriodNumber(){
		return this.periodNumber;
	}
	public void setPeriodNumber(String periodNumber){
		this.periodNumber = periodNumber;
	}
 	public String getPeriodType(){
		return this.periodType;
	}
	public void setPeriodType(String periodType){
		this.periodType = periodType;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServicePeriod that = (ServicePeriod) o;

        if (serviceAuditorium != null ? !serviceAuditorium.equals(that.serviceAuditorium) : that.serviceAuditorium != null) return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (discipline != null ? !discipline.equals(that.discipline) : that.discipline != null) return false;
        if (groupID != null ? !groupID.equals(that.groupID) : that.groupID != null) return false;
        if (lecturer != null ? !lecturer.equals(that.lecturer) : that.lecturer != null) return false;
        if (periodID != null ? !periodID.equals(that.periodID) : that.periodID != null) return false;
        if (periodNumber != null ? !periodNumber.equals(that.periodNumber) : that.periodNumber != null) return false;
        if (periodType != null ? !periodType.equals(that.periodType) : that.periodType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = serviceAuditorium != null ? serviceAuditorium.hashCode() : 0;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (groupID != null ? groupID.hashCode() : 0);
        result = 31 * result + (lecturer != null ? lecturer.hashCode() : 0);
        result = 31 * result + (periodID != null ? periodID.hashCode() : 0);
        result = 31 * result + (periodNumber != null ? periodNumber.hashCode() : 0);
        result = 31 * result + (periodType != null ? periodType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServicePeriod{" +
                "auditorium=" + serviceAuditorium +
                ", day='" + day + '\'' +
                ", discipline='" + discipline + '\'' +
                ", groupID='" + groupID + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", periodID='" + periodID + '\'' +
                ", periodNumber='" + periodNumber + '\'' +
                ", periodType='" + periodType + '\'' +
                '}';
    }
}
