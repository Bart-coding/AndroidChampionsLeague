package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface GroupMatchdayService {

    @GET
    Call<Object> getMatches(@Url String url);

    //@GET("v2/competitions/CL/matches?season=2019&stage=GROUP_STAGE&matchday=1")
    //Call<Object> getMatches();

    //Call<Object> getMatches(@Query("matchday") String matchday);
    //@GET("v2/competitions/CL/matches?season=2019&stage=GROUP_STAGE&matchday={Query}")
    //Call<Object> getMatches(@Query("matchday") int matchday); //String
    //Call<Object> getMatches(@Path(value = "id", encoded = true) int id); //String
    //Call<Object> getMatches();
}
