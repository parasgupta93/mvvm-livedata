package com.paras.mostviewednews.webservice;

import com.paras.mostviewednews.data.pojo.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("mostpopular/v2/viewed/{period}.json")
    Call<Response> getNewsList(@Path("period") int period, @Query("api-key") String apiKey);
}
