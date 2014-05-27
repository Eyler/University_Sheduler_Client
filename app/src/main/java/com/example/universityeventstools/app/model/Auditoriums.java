
package com.example.universityeventstools.app.model;

import com.google.gson.annotations.SerializedName;

public class Auditoriums{

    @SerializedName("auditoriumID")
   	private String auditoriumID;

    @SerializedName("auditoriumLocation")
   	private String auditoriumLocation;

    @SerializedName("auditoriumNumber")
   	private String auditoriumNumber;

    public Auditoriums(String auditoriumID, String auditoriumLocation, String auditoriumNumber) {
        this.auditoriumID = auditoriumID;
        this.auditoriumLocation = auditoriumLocation;
        this.auditoriumNumber = auditoriumNumber;
    }

    public String getAuditoriumID(){
		return this.auditoriumID;
	}
	public void setAuditoriumID(String auditoriumID){
		this.auditoriumID = auditoriumID;
	}
 	public String getAuditoriumLocation(){
		return this.auditoriumLocation;
	}
	public void setAuditoriumLocation(String auditoriumLocation){
		this.auditoriumLocation = auditoriumLocation;
	}
 	public String getAuditoriumNumber(){
		return this.auditoriumNumber;
	}
	public void setAuditoriumNumber(String auditoriumNumber){
		this.auditoriumNumber = auditoriumNumber;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Auditoriums that = (Auditoriums) o;

        if (auditoriumID != null ? !auditoriumID.equals(that.auditoriumID) : that.auditoriumID != null) return false;
        if (auditoriumLocation != null ? !auditoriumLocation.equals(that.auditoriumLocation) : that.auditoriumLocation != null)
            return false;
        if (auditoriumNumber != null ? !auditoriumNumber.equals(that.auditoriumNumber) : that.auditoriumNumber != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auditoriumID != null ? auditoriumID.hashCode() : 0;
        result = 31 * result + (auditoriumLocation != null ? auditoriumLocation.hashCode() : 0);
        result = 31 * result + (auditoriumNumber != null ? auditoriumNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Auditoriums{" +
                "auditoriumID='" + auditoriumID + '\'' +
                ", auditoriumLocation='" + auditoriumLocation + '\'' +
                ", auditoriumNumber='" + auditoriumNumber + '\'' +
                '}';
    }
}
