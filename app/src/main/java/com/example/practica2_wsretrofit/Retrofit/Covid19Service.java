package com.example.practica2_wsretrofit.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface Covid19Service {
    @Headers({
            "x-rapidapi-key: 9f0ce76771msh83b90ea92683a77p1d24b7jsn781dc1e65c4c",
            "x-rapidapi-host: covid-19-data.p.rapidapi.com"
    })
    @GET("country")
    Call<List<CovidData>> getCovidDataByCountry(@Query("name") String country);
}
