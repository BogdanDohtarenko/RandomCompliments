package com.ideasapp.randomcompliments

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://www.stylecraze.com/articles/compliments-for-girls/")
    fun getComplimentsPage(): Call<String>
}