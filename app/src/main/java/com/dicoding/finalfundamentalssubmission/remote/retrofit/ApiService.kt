package com.dicoding.finalfundamentalssubmission.remote.retrofit

import com.dicoding.finalfundamentalssubmission.remote.response.GithubResponse
import com.dicoding.finalfundamentalssubmission.remote.response.ItemsItem
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("search/users")
    fun getGithubUserData(
        @Query("q") q: String,
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String,
    ): Call <ItemsItem>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String,
    ): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String,
    ): Call<List<ItemsItem>>

}