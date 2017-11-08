package com.oscar.nat_at.Data.Retrofit;

import com.oscar.nat_at.Data.Model.SearchResult;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by oemy9 on 07/11/2017.
 */

public interface APIGoogleService {
    @GET("place/nearbysearch/json")
    Call<SearchResult> obtenerLugares(@Query("location")String location, @Query("radius")
                                      int radius);
}
