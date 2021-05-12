package com.example.rebajitasdiego.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.rebajitasdiego.models.EatingData
import com.example.rebajitasdiego.models.FoodCategory
import kotlinx.coroutines.flow.*

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val maxEatingValues = listOf(3,3,6,5,3,3)
    private val _foodCategoriesData = MutableStateFlow(
        FoodCategory.values().mapIndexed { index, foodCategory ->
            EatingData(foodCategory, 0, maxEatingValues[index], false)
        }
    )

    val foodCategoriesData: StateFlow<List<EatingData>> get() = _foodCategoriesData

    fun getFoodCategoryData(category: FoodCategory): EatingData? {
        return _foodCategoriesData.value.find { eatingData -> eatingData.category == category }

    }

    fun setFoodCategoryData(category: FoodCategory, newEatingData: EatingData): Unit {
        val newFoodData = _foodCategoriesData.value.map { eatingData ->
            if (eatingData.category == category) {
                newEatingData
            } else {
                eatingData
            }

        }

        _foodCategoriesData.value = newFoodData
    }

    fun onAddEating(category: FoodCategory): Unit {

        val categoryToUpdate = getFoodCategoryData(category)

        if (categoryToUpdate != null) {
            val newFoodCategoryData = addEatingToFoodData(categoryToUpdate)
            _foodCategoriesData.value = _foodCategoriesData.value.map { foodData ->
                if (foodData.category == category) {
                    newFoodCategoryData
                } else {
                    foodData
                }
            }
        }
    }


    fun onRemoveEating(category: FoodCategory): Unit {

        val categoryToUpdate = getFoodCategoryData(category)
        if (categoryToUpdate != null) {
            val newFoodCategoryData = removeEatingToFoodData(categoryToUpdate)
            _foodCategoriesData.value = _foodCategoriesData.value.map { foodData ->
                if (foodData.category == category) {
                    newFoodCategoryData
                } else {
                    foodData
                }
            }
        }
    }

    private fun addEatingToFoodData(eatingData: EatingData): EatingData {
        return if (eatingData.isLastHalf) {
            EatingData(
                eatingData.category,
                eatingData.eatingValue + 1,
                eatingData.maxEatingValue,
                false
            )
        } else {
            EatingData(eatingData.category, eatingData.eatingValue, eatingData.maxEatingValue, true)
        }
    }

    private fun removeEatingToFoodData(eatingData: EatingData): EatingData {
        return if (eatingData.isLastHalf) {
            EatingData(
                eatingData.category,
                eatingData.eatingValue,
                eatingData.maxEatingValue,
                false
            )
        } else {
            EatingData(
                eatingData.category,
                (eatingData.eatingValue - 1).coerceAtLeast(0),
                eatingData.maxEatingValue,
                if(eatingData.eatingValue == 0) false else true
            )
        }
    }

}
