package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamsListService {
    @GET("v2/competitions/CL/teams?stage=GROUP_STAGE")
    Call<Object> getTeams();
}
