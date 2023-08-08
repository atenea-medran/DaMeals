package com.atenea.dameals.data.remote

import com.atenea.dameals.data.remote.dto.MealsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MealApi {

    @GET("api/json/v1/1/search.php?f=b")
    suspend fun getMealList(): MealsDto

    @GET("api/json/v1/1/lookup.php?")
    suspend fun getMealDetail(@Query("i") mealId: String): MealsDto?

}