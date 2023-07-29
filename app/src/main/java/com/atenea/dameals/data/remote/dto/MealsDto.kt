package com.atenea.dameals.data.remote.dto

import com.squareup.moshi.Json

data class MealsDto(
    @Json(name = "meals") val meals: List<MealDto> = listOf()

)