package com.yusufguler.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yusufguler.foody.models.Result
import com.yusufguler.foody.util.Constants.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
    var result:Result
)