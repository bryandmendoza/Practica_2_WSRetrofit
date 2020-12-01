package com.example.practica2_wsretrofit.Retrofit;

public class CovidData {
    private String country;
    private String code;
    private String confirmed;
    private String recovered;
    private String critical;
    private String deaths;
    private String latitude;
    private String longitude;
    private String lastChange;
    private String lastUpdate;

    public String getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    public String getConfirmed() {
        return confirmed;
    }

    public String getRecovered() {
        return recovered;
    }

    public String getCritical() {
        return critical;
    }

    public String getDeaths() {
        return deaths;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLastChange() {
        return lastChange;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
