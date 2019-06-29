package com.example.hotornot.model.helper;

public class City {
    private String name;
    private String country;

    public City() {
    }

    public City(final String name, final String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }
}
