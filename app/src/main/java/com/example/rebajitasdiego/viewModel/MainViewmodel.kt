package com.example.rebajitasdiego.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rebajitasdiego.db.RebajitasDatabase
import com.example.rebajitasdiego.db.entities.EatingDataEntity
import com.example.rebajitasdiego.db.entities.UserProfileEntity
import com.example.rebajitasdiego.mappers.isMale
import com.example.rebajitasdiego.mappers.toEatingData
import com.example.rebajitasdiego.mappers.toEatingDataEntity
import com.example.rebajitasdiego.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(app: Application) : AndroidViewModel(app) {
    private val db = RebajitasDatabase.init(getApplication())

    private val _userProfile = db.userProfileDao().getUser()

     val userProfile = _userProfile

    private val _foodCategoriesData =
        db
            .eatingDataDao()
            .getAllEatingData()
            .onEach {
                Log.d("db data", it.toString())
            }
            .combine(userProfile) { eatingDataEntities, userProfile ->
                val maxEatingValues = if (userProfile!!.isMale) MaleMaxEatingValues
                else FemaleMaxEatingValues
                val foodCategories = FoodCategory.values().toList()
                eatingDataEntities.map {
                    it.toEatingData(userProfile!!.isMale)
                }

            }.onEach {
                Log.d("food data", it.toString())
            }

    val foodCategoriesData: Flow<List<EatingData>> get() = _foodCategoriesData


    fun onClearAllData(){
        Log.d("clear data form db", "Clearing data")
        viewModelScope.launch(Dispatchers.IO) {
            db.eatingDataDao().clearAllData()
        }
    }


    fun onAddEating(eatingData: EatingData): Unit {

        val newFoodCategoryData = addEatingToFoodData(eatingData)
        val eatingDataEntity = newFoodCategoryData.toEatingDataEntity(true)
        viewModelScope.launch(Dispatchers.IO) {
            db.eatingDataDao().updateOne(eatingDataEntity)
        }
    }


    fun onRemoveEating(eatingData: EatingData): Unit {

        val newFoodCategoryData = removeEatingToFoodData(eatingData)
        val eatingDataEntity = newFoodCategoryData.toEatingDataEntity(true)
        viewModelScope.launch(Dispatchers.IO) {
            db.eatingDataDao().updateOne(eatingDataEntity)
        }
    }

    private fun addEatingToFoodData(eatingData: EatingData): EatingData {
        return if (eatingData.isLastHalf) {
            EatingData(
                eatingData.id,
                eatingData.eatingValue + 1,
                eatingData.category,
                eatingData.maxEatingValue,
                false
            )
        } else {
            EatingData(
                eatingData.id,
                eatingData.eatingValue,
                eatingData.category,
                eatingData.maxEatingValue,
                true
            )
        }
    }

    private fun removeEatingToFoodData(eatingData: EatingData): EatingData {
        return if (eatingData.isLastHalf) {
            EatingData(
                eatingData.id,
                eatingData.eatingValue,
                eatingData.category,
                eatingData.maxEatingValue,
                false
            )
        } else {
            EatingData(
                eatingData.id,
                (eatingData.eatingValue - 1).coerceAtLeast(0),
                eatingData.category,
                eatingData.maxEatingValue,
                eatingData.eatingValue != 0
            )
        }
    }

    fun onChangeGender(newUser: UserProfileEntity): Unit {
        viewModelScope.launch(Dispatchers.IO) {
            db.userProfileDao().updateUser(newUser)
        }
    }

}
