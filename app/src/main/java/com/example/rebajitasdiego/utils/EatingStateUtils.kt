package com.example.rebajitasdiego.utils

import androidx.compose.runtime.Composable
import com.example.rebajitasdiego.components.EmptyCircle
import com.example.rebajitasdiego.components.FilledCircle
import com.example.rebajitasdiego.components.HalfFilledCircle
import com.example.rebajitasdiego.models.EatingState

@Composable
    fun getCircleIconForEatingState(state: EatingState) =
        when (state) {
            EatingState.EMPTY -> EmptyCircle()
            EatingState.HALF -> HalfFilledCircle()
            EatingState.FULL -> FilledCircle()
        }
