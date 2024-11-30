package com.example.movieapp.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.movieapp.R;
import com.example.movieapp.serviceapi.MovieApiService;
import com.example.movieapp.serviceapi.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MovieRepository {


    // Used to abstract the data source details and
    // provide a clean API for the ViewModel to
    // fetch and manage data


    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public MovieRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLLiveData(){
        MovieApiService movieApiService = RetrofitInstance.getService();
        Call<Result> call = movieApiService.getPopularMovies(application.getApplicationContext().getString(R.string.api_key));

        // perform network request in the background thread and
        // handle the response on the main (ui) thread
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                // success
                Result result = response.body();
                if (result != null && result.getResults() != null){
                    movies = (ArrayList<Movie>) result.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });

        return mutableLiveData;

    }
}
