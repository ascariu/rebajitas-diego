package com.example.rebajitasdiego.utils

import androidx.compose.runtime.Composable
import com.example.rebajitasdiego.components.EmptyCircle
import com.example.rebajitasdiego.components.FilledCircle
import com.example.rebajitasdiego.components.HalfFilledCircle
import com.example.rebajitasdiego.models.EatingData
import com.example.rebajitasdiego.models.EatingState
import com.example.rebajitasdiego.models.FoodCategory

@Composable
fun getCircleIconForEatingState(state: EatingState) =
    when (state) {
        EatingState.EMPTY -> EmptyCircle()
        EatingState.HALF -> HalfFilledCircle()
        EatingState.FULL -> FilledCircle()
        EatingState.FULL_EXTRA -> FilledCircle(isExtra = true)
        EatingState.HALF_EXTRA -> HalfFilledCircle(isExtra = true)
    }

