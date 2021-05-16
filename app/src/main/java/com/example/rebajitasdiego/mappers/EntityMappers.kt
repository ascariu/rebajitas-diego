package com.example.rebajitasdiego.mappers

import com.example.rebajitasdiego.db.entities.EatingDataEntity
import com.example.rebajitasdiego.db.entities.UserProfileEntity
import com.example.rebajitasdiego.models.*

fun EatingDataEntity.toEatingData(isMale: Boolean): EatingData {
    return EatingData(
        id,
        counter,
        category,
        maxEatingValue = getMaxEatingValueOfGenderAndCategory(isMale, category),
        isLastHalf
    )
}

fun EatingData.toEatingDataEntity(isMale: Boolean): EatingDataEntity {
    return EatingDataEntity(
        id,
        eatingValue,
        category,
        isLastHalf
    )
}

val UserProfileEntity.isMale: Boolean get() = this.gender == Gender.Male
val UserProfile.isMale: Boolean get() = this.gender == Gender.Male

fun UserProfileEntity.toUserProfile(): UserProfile {
    return UserProfile(id, gender, name)
}

fun UserProfile.toUserProfileEntity(): UserProfileEntity {
    return UserProfileEntity(id, gender= gender, name = name)
}

fun getMaxEatingValueOfGenderAndCategory(isMale: Boolean, category: FoodCategory): Int =
    if (isMale) MaleMaxEatingValues.getForCategory(category)
    else FemaleMaxEatingValues.getForCategory(category)