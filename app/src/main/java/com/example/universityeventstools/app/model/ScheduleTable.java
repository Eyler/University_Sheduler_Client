package com.example.universityeventstools.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ykoby_000 on 20.05.2014.
 */
public class ScheduleTable {

    private String day;

    private String periodNumber;

    private String discipline;

    private String lecturer;

    private String auditoriumLocation;

    private String auditoriumNumber;

    public ScheduleTable(String day, String periodNumber, String discipline, String lecturer, String auditoriumLocation, String auditoriumNumber) {
        this.day = day;
        this.periodNumber = periodNumber;
        this.discipline = discipline;
        this.lecturer = lecturer;
        this.auditoriumLocation = auditoriumLocation;
        this.auditoriumNumber = auditoriumNumber;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(String periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getAuditoriumLocation() {
        return auditoriumLocation;
    }

    public void setAuditoriumLocation(String auditoriumLocation) {
        this.auditoriumLocation = auditoriumLocation;
    }

    public String getAuditoriumNumber() {
        return auditoriumNumber;
    }

    public void setAuditoriumNumber(String auditoriumNumber) {
        this.auditoriumNumber = auditoriumNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduleTable that = (ScheduleTable) o;

        if (auditoriumLocation != null ? !auditoriumLocation.equals(that.auditoriumLocation) : that.auditoriumLocation != null)
            return false;
        if (auditoriumNumber != null ? !auditoriumNumber.equals(that.auditoriumNumber) : that.auditoriumNumber != null)
            return false;
        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (discipline != null ? !discipline.equals(that.discipline) : that.discipline != null) return false;
        if (lecturer != null ? !lecturer.equals(that.lecturer) : that.lecturer != null) return false;
        if (periodNumber != null ? !periodNumber.equals(that.periodNumber) : that.periodNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (periodNumber != null ? periodNumber.hashCode() : 0);
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (lecturer != null ? lecturer.hashCode() : 0);
        result = 31 * result + (auditoriumLocation != null ? auditoriumLocation.hashCode() : 0);
        result = 31 * result + (auditoriumNumber != null ? auditoriumNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return day + '\t' + periodNumber +'\t' + discipline + '\n' +
                '\t' + '\t' + lecturer + '\t' + auditoriumLocation + '\t' + auditoriumNumber;

    }
}
