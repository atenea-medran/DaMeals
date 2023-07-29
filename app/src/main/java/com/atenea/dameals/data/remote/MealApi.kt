package com.atenea.dameals.data.remote

import com.atenea.dameals.data.remote.dto.MealsDto
import retrofit2.http.GET

interface MealApi {

    @GET("api/json/v1/1/search.php?f=b")
    suspend fun getMealList(): MealsDto

}