package com.nativedevps.domain.datasources.remote.flickr

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {

    //https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=9a95c68a9c6ec61104cd3967dcbb8bd3&tags=kitten&page=1&per_page=20&format=json&nojsoncallback=1
    @GET("rest/")
    suspend fun fetchImageDataAsync(
        @Query("method") method: String, @Query("api_key") apiKey: String,
        @Query("tags") tags: String, @Query("page") page: Int,
        @Query("per_page") perPage: Int, @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Long,
    ): Map<String, Any>

    @GET("rest/")
    suspend fun fetchSingleImageAsync(
        @Query("method") method: String, @Query("api_key") apiKey: String,
        @Query("photo_id") tags: String, @Query("format") format: String,
        @Query("nojsoncallback") nojsoncallback: Long,
    ): Response<Any>


}