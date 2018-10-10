package com.example.shadow47.movies;

public class weather {
    private String timeZone, summary,ws,humid,temp;

    public String getTimeZone() {
        return timeZone;
    }

    public String getSummary() {
        return summary;
    }

    public String getWs() {
        return ws;
    }

    public String getHumid() {
        return humid;
    }

    public String getTemp() {
        return temp;
    }

    public weather(String timeZone, String summary, String ws, String humid, String temp) {
        this.timeZone = timeZone;
        this.summary = summary;
        this.ws = ws;
        this.humid = humid;
        this.temp = temp;
    }

}

