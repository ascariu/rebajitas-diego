package com.example.rebajitasdiego.models

import com.example.rebajitasdiego.db.entities.EatingDataEntity
import kotlin.math.E


data class EatingData(
    val id: Int,
    val eatingValue: Int,
    val category: FoodCategory,
    val maxEatingValue: Int,
    val isLastHalf: Boolean
) {

    fun getCircle(currentEatingValue: Int): EatingState {

        return when {
            currentEatingValue <= maxEatingValue && currentEatingValue <= eatingValue -> EatingState.FULL

            currentEatingValue <= maxEatingValue && currentEatingValue == eatingValue + 1 && isLastHalf -> EatingState.HALF

            currentEatingValue <= maxEatingValue && currentEatingValue == eatingValue + 1 && !isLastHalf -> EatingState.EMPTY

            currentEatingValue <= maxEatingValue && currentEatingValue > eatingValue + 1 && maxEatingValue > eatingValue -> EatingState.EMPTY

            currentEatingValue > maxEatingValue && currentEatingValue == eatingValue + 1 && isLastHalf -> EatingState.HALF_EXTRA

            currentEatingValue > maxEatingValue && currentEatingValue <= eatingValue -> EatingState.FULL_EXTRA
            currentEatingValue > maxEatingValue && currentEatingValue == eatingValue + 1 -> EatingState.HALF_EXTRA


            else -> getEmptyCircle()
        }
    }

    private fun getEmptyCircle(): EatingState {
        return EatingState.EMPTY
    }

}