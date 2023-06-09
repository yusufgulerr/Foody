package com.yusufguler.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusufguler.foody.models.FoodRecipe
import com.yusufguler.foody.util.Constants.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
 var foodRecipe: FoodRecipe
){
    @PrimaryKey(autoGenerate = false)
    var id :Int =0
}