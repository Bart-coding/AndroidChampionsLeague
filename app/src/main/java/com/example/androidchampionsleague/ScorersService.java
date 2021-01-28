package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ScorersService {
    /*@Headers({
            "X-Auth-Token: fc23b99dfbe1435bae465f0b137fa7ca",
            "Cache-Control: max-age=640000"
    })*/
    @GET("v2/competitions/CL/scorers?season=2019")
    Call<Object> getScorers();
}
