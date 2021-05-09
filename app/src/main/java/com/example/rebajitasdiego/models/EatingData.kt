package com.example.rebajitasdiego.models


data class EatingData(
    val eatingValue: Int,
    val maxEatingValue: Int,
    val isLastHalf: Boolean
) {

    fun getCircle(currentEatingValue: Int): EatingState {
        return when {
            currentEatingValue <= maxEatingValue && currentEatingValue < eatingValue -> EatingState.FULL
            currentEatingValue <= maxEatingValue && currentEatingValue > eatingValue -> EatingState.EMPTY
            currentEatingValue > maxEatingValue && currentEatingValue < eatingValue -> EatingState.FULL
            currentEatingValue == currentEatingValue -> getLastCircle(
                currentEatingValue
            )
            else -> getEmptyCircle()
        }
    }

    private fun getLastCircle(currentEatingValue: Int): EatingState {
        return when {
            currentEatingValue < maxEatingValue -> EatingState.FULL
            currentEatingValue == maxEatingValue && isLastHalf -> EatingState.HALF
            currentEatingValue == maxEatingValue && !isLastHalf -> EatingState.FULL
            currentEatingValue > maxEatingValue -> EatingState.EMPTY
            else -> error("Eso no possible ser")
        }
    }

    private fun getEmptyCircle(): EatingState {
        return EatingState.EMPTY
    }
}