package com.ideasapp.randomcompliments.ui.theme.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    onClick: () -> Unit,
    currentCompliment: String
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(10.dp).fillMaxSize()
        ) {
            Text(
                text = "Current compliment:",
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = currentCompliment,
                modifier = Modifier.padding(10.dp)
            )
            Button(
                onClick = { onClick },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = "Find new compliment")
            }
        }
    }
}