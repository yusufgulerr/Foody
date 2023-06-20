package com.yusufguler.foody.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.yusufguler.foody.data.database.entities.RecipesEntity
import com.yusufguler.foody.models.FoodRecipe
import com.yusufguler.foody.util.NetworkResult

class RecipesBinding {

    companion object {
        @BindingAdapter("readApiResponse","readDatabase", requireAll = true)
        @JvmStatic
        fun errorImageViewVisibilty(
            view:ImageView,
            apiResponse:NetworkResult<FoodRecipe>?,
            database:List<RecipesEntity>?
        ){
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()){
                view.visibility = View.VISIBLE
            }else if (apiResponse is NetworkResult.Loading){
                view.visibility = View.INVISIBLE
            }else if (apiResponse is NetworkResult.Success){
                view.visibility = View.INVISIBLE
            }
        }
        @BindingAdapter("readApiResponse2","readDatabase2", requireAll = true)
        @JvmStatic
        fun errorTextViewVisibilty(
            textView: TextView,
            apiResponse:NetworkResult<FoodRecipe>?,
            database:List<RecipesEntity>?
        ){
            if (apiResponse is NetworkResult.Error && database.isNullOrEmpty()){
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            }else if (apiResponse is NetworkResult.Loading){
                textView.visibility = View.INVISIBLE
            }else if (apiResponse is NetworkResult.Success){
                textView.visibility = View.INVISIBLE
            }
        }



    }
}