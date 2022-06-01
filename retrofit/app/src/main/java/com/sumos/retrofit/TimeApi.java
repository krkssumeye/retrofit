package com.sumos.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TimeApi {

    @GET("Europe/Istanbul")
    Call<time_istanbul> getTime();
}
