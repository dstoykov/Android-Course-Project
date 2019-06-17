package com.example.hotornot.model;

public class Weather {
    private String main;
    private String description;

    public Weather() {
    }

    public Weather(final String main, final String description) {
        this.main = main;
        this.description = description;
    }

    public String getMain() {
        return this.main;
    }

    public void setMain(final String main) {
        this.main = main;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }
}
