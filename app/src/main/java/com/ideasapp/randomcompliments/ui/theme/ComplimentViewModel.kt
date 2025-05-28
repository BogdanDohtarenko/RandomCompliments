package com.ideasapp.randomcompliments.ui.theme

import android.util.Log
import com.ideasapp.randomcompliments.ApiService

import com.ideasapp.randomcompliments.HtmlParser.fetchCompliments
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
    var complimentsList: List<String> = listOf()

    init {
        coroutineScope.launch {
            val compliments: List<String> = fetchCompliments()
            var count = 0
            for(compliment in compliments) {
                count++
                Log.d(TAG,"$count: $compliment")
            }
            complimentsList = compliments
        }
    }

    fun getNewCompliment(): String {
        return if (complimentsList.isNotEmpty()) complimentsList.random()
        else "Please click again later"
    }

    companion object {
        const val TAG:String = "CallCompliment"
    }
}