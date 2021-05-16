package com.example.rebajitasdiego.models

sealed class MaxEatingValues(){
    abstract fun getForCategory(category: FoodCategory): Int

    class MaleMaxEatingValues() : MaxEatingValues() {
        override fun getForCategory(category: FoodCategory): Int =
            when(category){
                FoodCategory.DAIRY -> 3
                FoodCategory.OIL -> 3
                FoodCategory.PROTEIN -> 6
                FoodCategory.BREAD -> 5
                FoodCategory.FRUIT -> 3
                FoodCategory.VEGETABLE -> 3
            }
    }

    class FemaleMaxEatingValues() : MaxEatingValues() {
        override fun getForCategory(category: FoodCategory): Int =
            when(category){
                FoodCategory.DAIRY -> 2
                FoodCategory.OIL -> 2
                FoodCategory.PROTEIN -> 4
                FoodCategory.BREAD -> 3
                FoodCategory.FRUIT -> 2
                FoodCategory.VEGETABLE -> 3
            }
    }
}

val FemaleMaxEatingValues:MaxEatingValues = MaxEatingValues.FemaleMaxEatingValues()
val MaleMaxEatingValues:MaxEatingValues = MaxEatingValues.MaleMaxEatingValues()
