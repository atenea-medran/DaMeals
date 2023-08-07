package com.atenea.dameals.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atenea.dameals.data.local.model.MealLocal
import kotlinx.coroutines.flow.Flow

@Dao
public interface MealDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mealList: List<MealLocal>)

    @Query("SELECT * FROM MealTable ORDER BY strMeal")
    suspend fun getAll(): List<MealLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealLocal)

    @Delete
    suspend fun deleteMeal(meal: MealLocal)
}