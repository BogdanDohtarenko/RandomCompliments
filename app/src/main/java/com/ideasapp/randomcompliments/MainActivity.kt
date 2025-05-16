package com.ideasapp.randomcompliments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ideasapp.randomcompliments.ui.theme.ComplimentViewModel
import com.ideasapp.randomcompliments.ui.theme.RandomComplimentsTheme
import com.ideasapp.randomcompliments.ui.theme.ui.MainScreen


//TODO
// 1. https://www.momjunction.com/articles/compliments-for-girls_00685873/
// 2. pars random compliment from this site by button clicking
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel = ComplimentViewModel()
        setContent {
            RandomComplimentsTheme {
                MainScreen(
                    onClick = { viewModel.getNewCompliment() },
                    currentCompliment = viewModel.currentCompliment
                )
            }
        }
    }
}
