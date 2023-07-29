package com.atenea.dameals.data.remote

import com.atenea.dameals.data.remote.dto.MealDto
import retrofit2.http.GET

interface MealApi {

    @GET("api/json/v1/1/search.php?f=a")
    suspend fun getMealList(): List<MealDto>

}