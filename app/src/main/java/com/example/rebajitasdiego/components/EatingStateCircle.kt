package com.example.rebajitasdiego.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun EmptyCircle() {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
        )
    }
}


@Composable
fun FilledCircle(isExtra: Boolean = false) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp)
                .background(color = if(isExtra) Color.Red else Color.White, shape = CircleShape)
        )
    }
}

@Composable
fun HalfFilledCircle(isExtra: Boolean = false) {
    Box(
        modifier = Modifier
            .padding(start = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(24.dp)
                .border(width = 1.dp, color = Color.White, shape = CircleShape)
        ) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color = if(isExtra) Color.Red else Color.White, shape = CircleShape)
                    .align(
                        Alignment.Center
                    )
            )
        }
    }
}