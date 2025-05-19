package com.ideasapp.randomcompliments

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {

    companion object {
        const val BASE_URL = "https://www.momjunction.com/articles/compliments-for-girls_00685873/"
        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        }
    }
}