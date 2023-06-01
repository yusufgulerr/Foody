package com.yusufguler.foody.data.network

import com.yusufguler.foody.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesAPI {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(
        @QueryMap queries: Map<String,String>
    ):Response<FoodRecipe>

}