package com.example.androidchampionsleague;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GroupsService {
    @GET("v2/competitions/CL/standings?standingType=TOTAL&season=2019")
    Call<Object> getGroups();
}
