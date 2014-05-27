
package com.example.universityeventstools.app.model;

import java.util.List;

public class ServiceSchedule {
    private List<ServicePeriod> servicePeriod;

    public List<ServicePeriod> getServicePeriod() {
        return this.servicePeriod;
    }

    public void setServicePeriod(List<ServicePeriod> servicePeriod) {
        this.servicePeriod = servicePeriod;
    }
}
