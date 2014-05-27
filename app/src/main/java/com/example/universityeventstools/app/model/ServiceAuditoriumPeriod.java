
package com.example.universityeventstools.app.model;

import java.util.List;

public class ServiceAuditoriumPeriod{
   	private List<Auditoriums> auditoriums;
   	private String periodNumber;

    public ServiceAuditoriumPeriod(List<Auditoriums> auditoriums, String periodNumber) {
        this.auditoriums = auditoriums;
        this.periodNumber = periodNumber;
    }

    public List<Auditoriums> getAuditoriums(){
		return this.auditoriums;
	}
	public void setAuditoriums(List<Auditoriums> auditoriums){
		this.auditoriums = auditoriums;
	}
 	public String getPeriodNumber(){
		return this.periodNumber;
	}
	public void setPeriodNumber(String periodNumber){
		this.periodNumber = periodNumber;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceAuditoriumPeriod that = (ServiceAuditoriumPeriod) o;

        if (auditoriums != null ? !auditoriums.equals(that.auditoriums) : that.auditoriums != null) return false;
        if (periodNumber != null ? !periodNumber.equals(that.periodNumber) : that.periodNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = auditoriums != null ? auditoriums.hashCode() : 0;
        result = 31 * result + (periodNumber != null ? periodNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceAuditoriumPeriod{" +
                "auditoriums=" + auditoriums +
                ", periodNumber='" + periodNumber + '\'' +
                '}';
    }
}
