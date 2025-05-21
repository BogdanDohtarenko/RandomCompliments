package com.ideasapp.randomcompliments.ui.theme

import android.util.Log
import com.ideasapp.randomcompliments.ApiService
import com.ideasapp.randomcompliments.HtmlParser.extractCompliments
import com.ideasapp.randomcompliments.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ComplimentViewModel {
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    var loadingCompleted: Boolean = false
    var complimentsList: List<String> = listOf()
    init {
        coroutineScope.launch {
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
                        loadingCompleted = true
                        complimentsList = compliments
                    } else {
                        Log.e(TAG,"Failed to fetch HTML content")
                    }
                }

                override fun onFailure(call:Call<String?>,t:Throwable) {
                    Log.e(TAG,"API call failed",t)
                }
            })
        }
    }

    fun getNewCompliment(): String {
        return if (loadingCompleted && complimentsList.isNotEmpty()) complimentsList.random()
        else "Please click again later"
    }

    companion object {
        const val TAG:String = "CallCompliment"
    }
}