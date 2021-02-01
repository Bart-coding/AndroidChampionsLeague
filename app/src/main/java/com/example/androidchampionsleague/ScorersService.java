package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ScorersService {
    @GET("v2/competitions/CL/scorers?season=2019")
    Call<Object> getScorers();
}
