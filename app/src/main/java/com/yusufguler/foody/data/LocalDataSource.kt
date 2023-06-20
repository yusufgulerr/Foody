package com.yusufguler.foody.data

import com.yusufguler.foody.data.database.RecipesDAO
import com.yusufguler.foody.data.database.entities.FavoritesEntity
import com.yusufguler.foody.data.database.entities.RecipesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipesDAO: RecipesDAO
) {
    fun readRecipes(): Flow<List<RecipesEntity>> {
        return recipesDAO.readRecipes()
    }

    fun readFavoriteRecipes() : Flow<List<FavoritesEntity>>{
        return recipesDAO.readFavoriteRecipes()
    }

    suspend fun insertRecipes(recipesEntity: RecipesEntity){
        recipesDAO.insertRecipes(recipesEntity)
    }

    suspend fun insertFavoriteRecipes(favoritesEntity: FavoritesEntity){
        recipesDAO.insertFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteFavoriteRecipe(favoritesEntity: FavoritesEntity){
        recipesDAO.deleteFavoriteRecipe(favoritesEntity)
    }

    suspend fun deleteAllFavoriteRecipes(){
        recipesDAO.deleteAllFavoriteRecipes()
    }
}