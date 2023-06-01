package com.yusufguler.foody.models


import com.google.gson.annotations.SerializedName
import com.yusufguler.foody.models.Result

data class FoodRecipe(
    @SerializedName("results")
    val results: List<Result>
)