package com.sapient.recipeapp.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sapient.recipeapp.data.local.entity.Converters
import com.sapient.recipeapp.data.local.entity.RecipeEntity

/**
 * The Room database for this app
 */
@Database(entities = [RecipeEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: RecipeDatabase? = null

        fun getInstance(context: Context): RecipeDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): RecipeDatabase {
            return Room.databaseBuilder(context, RecipeDatabase::class.java, "RecipeDb").build()
        }
    }
}
