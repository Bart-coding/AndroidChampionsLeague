package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TeamsService {
    @GET
    Call<Object> getTeam(@Url String url);
}
