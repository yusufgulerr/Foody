package com.yusufguler.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.yusufguler.foody.API_KEY
import com.yusufguler.foody.util.Constants.DEFAULT_DIET_TYPE
import com.yusufguler.foody.util.Constants.DEFAULT_MEAL_TYPE
import com.yusufguler.foody.util.Constants.DEFAULT_RECIPES_NUMBER
import com.yusufguler.foody.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.yusufguler.foody.util.Constants.QUERY_APIKEY
import com.yusufguler.foody.util.Constants.QUERY_DIET
import com.yusufguler.foody.util.Constants.QUERY_FILL_INGREDIENTS
import com.yusufguler.foody.util.Constants.QUERY_NUMBER
import com.yusufguler.foody.util.Constants.QUERY_TYPE

class RecipesViewModel(application: Application):AndroidViewModel(application){

     fun applyQueries():HashMap<String,String>{
        val queries : HashMap<String,String> = HashMap()
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_APIKEY] = API_KEY
        queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
}