package com.example.robosdk.Models;

public class LBFilterModel {
    public int month;
    public int year;
    public String display;

    public LBFilterModel(int month, int year, String display) {
        this.month = month;
        this.year = year;
        this.display = display;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
