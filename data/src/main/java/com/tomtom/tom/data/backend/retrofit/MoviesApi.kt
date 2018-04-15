package com.tomtom.tom.data.backend.retrofit

import com.tomtom.tom.domain.model.MoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/upcoming")
    fun getUpcoming(
            @Query("api_key") api_key: String,
            @Query("page") page: String,
            @Query("language") language: String
    ): Single<MoviesResponse>

}