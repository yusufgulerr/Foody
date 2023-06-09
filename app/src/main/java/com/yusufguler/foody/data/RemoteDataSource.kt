package com.yusufguler.foody.data

import com.yusufguler.foody.data.network.FoodRecipesAPI
import com.yusufguler.foody.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesAPI: FoodRecipesAPI
) {
    suspend fun getRecipes(queries:Map<String,String>) : Response<FoodRecipe>{
        return foodRecipesAPI.getRecipes(queries)
    }
    suspend fun searchRecipes(searchQuery:Map<String,String>) :Response<FoodRecipe>{
        return  foodRecipesAPI.searchRecipes(searchQuery)
    }
}