package com.ideasapp.randomcompliments.ui.theme.ui

import android.util.Log
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ideasapp.randomcompliments.R
import com.ideasapp.randomcompliments.ui.theme.ComplimentViewModel

@Composable
fun MainScreen() {
    val viewModel = ComplimentViewModel()
    val currentCompliment = remember { mutableStateOf("Click button please") }
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
            Spacer(modifier = Modifier.height(20.dp))
            if (currentCompliment.value == "Click button please" || currentCompliment.value == "Please click again later")
                BeatingHeartAnimation()
        }
    }
}

@Composable
fun BeatingHeartAnimation() {
    val infiniteTransition = rememberInfiniteTransition(label = "tractor")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "tractor"
    )

    Image(
        painter = painterResource(id = R.drawable.tractor),
        contentDescription = "Beating Heart",
        modifier = Modifier
            .size(100.dp)
            .scale(scale)
    )
}