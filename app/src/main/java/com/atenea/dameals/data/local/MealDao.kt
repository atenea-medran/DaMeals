package com.atenea.dameals.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atenea.dameals.data.local.model.MealLocal

@Dao
public interface MealDao {

    @Query("SELECT * FROM FavoriteMealTable")
    suspend fun getAll(): List<MealLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMeal(meal: MealLocal)

    @Delete
    suspend fun deleteMeal(meal: MealLocal)
}