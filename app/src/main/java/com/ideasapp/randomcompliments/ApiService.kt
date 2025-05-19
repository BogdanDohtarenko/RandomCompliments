package com.ideasapp.randomcompliments

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://www.momjunction.com/articles/compliments-for-girls_00685873/")
    fun getComplimentsPage(): Call<String>
}