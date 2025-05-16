package com.ideasapp.randomcompliments.ui.theme

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComplimentViewModel {
    fun getNewCompliment(): String {
        return "I love you"
    }
}