package com.handler.framework.fuser.model;

public class MovieWrapper {
    private String movieName;
    private String startDate;
    private String endDate;

    public MovieWrapper() {
        this.movieName = "";
        this.startDate = "";
        this.endDate = "";
    }

    public MovieWrapper(String movieName, String startDate, String endDate) {
        this.movieName = movieName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public String getMovieName() {
        return this.movieName;
    }
    public String getStartDate() {
        return this.startDate;
    }
    public String getEndDate() {
        return this.endDate;
    }
}