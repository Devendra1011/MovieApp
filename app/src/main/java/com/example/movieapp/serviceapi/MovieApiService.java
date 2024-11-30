package com.example.movieapp.serviceapi;

import com.example.movieapp.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiService {


    // The Service interface defines the structure
    // and behaviour of the API requests
    // Act as a bridge between your app and the API


    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);



}
