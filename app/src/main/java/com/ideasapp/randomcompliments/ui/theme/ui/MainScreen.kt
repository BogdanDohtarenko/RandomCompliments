package com.ideasapp.randomcompliments.ui.theme.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ideasapp.randomcompliments.ui.theme.ComplimentViewModel

@Composable
fun MainScreen() {
    val viewModel = ComplimentViewModel()
    val currentCompliment = remember { mutableStateOf("!") }
    Log.d("ViewModel", "currentCompliment: $currentCompliment")
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Current compliment:",
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = currentCompliment.value,
                modifier = Modifier.padding(10.dp)
            )
            Button(
                onClick = { currentCompliment.value = viewModel.getNewCompliment() },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Find new compliment")
            }
        }
    }
}