package com.yusufguler.foody.viewmodels

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.yusufguler.foody.API_KEY
import com.yusufguler.foody.data.DataStoreRepository
import com.yusufguler.foody.util.Constants.DEFAULT_DIET_TYPE
import com.yusufguler.foody.util.Constants.DEFAULT_MEAL_TYPE
import com.yusufguler.foody.util.Constants.DEFAULT_RECIPES_NUMBER
import com.yusufguler.foody.util.Constants.QUERY_ADD_RECIPE_INFORMATION
import com.yusufguler.foody.util.Constants.QUERY_APIKEY
import com.yusufguler.foody.util.Constants.QUERY_DIET
import com.yusufguler.foody.util.Constants.QUERY_FILL_INGREDIENTS
import com.yusufguler.foody.util.Constants.QUERY_NUMBER
import com.yusufguler.foody.util.Constants.QUERY_SEARCH
import com.yusufguler.foody.util.Constants.QUERY_TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RecipesViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {
    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkStatus = false
    var backOnline = false
    val readMealAndDietType = dataStoreRepository.readMealAndDietType
    val readBackOnline = dataStoreRepository.readBackOnline.asLiveData()
    fun saveMealAndDietType(mealType:String,mealTypeId:Int,mealDiet:String,mealDietId:Int)=
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveMealAndDietType(mealType,mealTypeId,mealDiet,mealDietId)
        }
    private fun saveBackOnline(backOnline: Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveBackOnline(backOnline)
        }
    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        viewModelScope.launch {
            readMealAndDietType.collect{value->
                mealType = value.selectedMealType
                dietType = value.selectedDietType
            }
        }
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_APIKEY] = API_KEY
        queries[QUERY_TYPE] = mealType
        queries[QUERY_DIET] = dietType
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
    fun applySearchQuery(searchQuery:String):HashMap<String,String>{
        val queries:HashMap<String,String> = HashMap()
        queries[QUERY_SEARCH] = searchQuery
        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_APIKEY] = API_KEY
        queries[QUERY_ADD_RECIPE_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries
    }
    fun showNetworkStatus(){
        if (!networkStatus) {
            Toast.makeText(getApplication(), "No Internet Connection.", Toast.LENGTH_SHORT).show()
            saveBackOnline(true)
        } else if (networkStatus) {
            if (backOnline) {
                Toast.makeText(getApplication(), "We're back online.", Toast.LENGTH_SHORT).show()
                saveBackOnline(false)
            }
        }
    }


}