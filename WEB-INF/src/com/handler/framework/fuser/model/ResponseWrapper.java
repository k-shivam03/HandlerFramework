package com.handler.framework.fuser.model;

import java.util.*;

public class ResponseWrapper {

    private List<MovieWrapper> moviesSuggested;
    private String earning;

    public ResponseWrapper() {
        this.moviesSuggested = null;
        this.earning = "";
    }

    public ResponseWrapper(List<MovieWrapper> moviesSuggested, String earning) {
        this.moviesSuggested = moviesSuggested;
        this.earning = earning;
    }

    public void setMoviesSuggested(List<MovieWrapper> moviesSuggested) {
        this.moviesSuggested = moviesSuggested;
    }
    public void setEarning(String earning) {
        this.earning = earning;
    }

    public List<MovieWrapper> getMoviesSuggested() {
        return this.moviesSuggested;
    }
    public String getEarning() {
        return this.earning;
    }
}