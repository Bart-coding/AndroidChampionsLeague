package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MatchesService {

    @GET("v2/competitions/CL/matches?season=2019")
    Call<Object> getMatches(@Query("stage") String stage, @Query("matchday") Integer matchday);
}
