package com.yusufguler.foody.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.yusufguler.foody.data.database.entities.FavoritesEntity
import com.yusufguler.foody.data.database.entities.FoodJokeEntity
import com.yusufguler.foody.data.database.entities.RecipesEntity

@Database(
    entities = [RecipesEntity::class,FavoritesEntity::class,FoodJokeEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {
    abstract fun recipesDao(): RecipesDAO
}