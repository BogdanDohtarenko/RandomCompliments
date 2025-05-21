package com.ideasapp.randomcompliments.ui.theme

import android.util.Log
import com.ideasapp.randomcompliments.ApiService
import com.ideasapp.randomcompliments.HtmlParser.extractCompliments
import com.ideasapp.randomcompliments.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComplimentViewModel {
    init {
        val apiService:ApiService = RetrofitClient.retrofit.create(ApiService::class.java)
        val call:Call<String> = apiService.getComplimentsPage()

        call.enqueue(object : Callback<String?> {
            override fun onResponse(call:Call<String?>,response:Response<String?>) {
                if (response.isSuccessful && response.body() != null) {
                    val string = response.body()
                    val compliments: List<String> = string?.extractCompliments() ?: listOf()
                    var count = 0
                    for(compliment in compliments) {
                        count++
                        Log.d(TAG,"$count: $compliment")
                    }
                } else {
                    Log.e(TAG,"Failed to fetch HTML content")
                }
            }

            override fun onFailure(call:Call<String?>,t:Throwable) {
                Log.e(TAG,"API call failed",t)
            }
        })
    }
    fun getNewCompliment(): String {
        return "I love you"
    }
    companion object {
        const val TAG:String = "CallCompliment"
    }
}