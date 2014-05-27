
package com.example.universityeventstools.app.model;

import java.util.List;

public class FreeAud {
   	private List<ServiceAuditoriumPeriod> serviceAuditoriumPeriod;

 	public List<ServiceAuditoriumPeriod> getServiceAuditoriumPeriod(){
		return this.serviceAuditoriumPeriod;
	}
	public void setServiceAuditoriumPeriod(List<ServiceAuditoriumPeriod> serviceAuditoriumPeriod){
		this.serviceAuditoriumPeriod = serviceAuditoriumPeriod;
	}

    public FreeAud(List<ServiceAuditoriumPeriod> serviceAuditoriumPeriod) {
        this.serviceAuditoriumPeriod = serviceAuditoriumPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FreeAud freeAud = (FreeAud) o;

        if (serviceAuditoriumPeriod != null ? !serviceAuditoriumPeriod.equals(freeAud.serviceAuditoriumPeriod) : freeAud.serviceAuditoriumPeriod != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return serviceAuditoriumPeriod != null ? serviceAuditoriumPeriod.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "FreeAud{" +
                "serviceAuditoriumPeriod=" + serviceAuditoriumPeriod +
                '}';
    }
}
