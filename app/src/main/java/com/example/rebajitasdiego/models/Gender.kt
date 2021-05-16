package com.example.rebajitasdiego.models

enum class Gender {
    Male,
    Female;

    override fun toString(): String {
        return if(this == Gender.Male) "Hombre" else "Mujer"
    }
}
