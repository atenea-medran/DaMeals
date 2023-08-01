package com.atenea.dameals.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atenea.dameals.data.local.model.MealLocal

@Database(entities = [MealLocal::class], version = 1, exportSchema = false)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao
}