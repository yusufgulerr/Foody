package com.yusufguler.foody.data

import android.content.Context
import android.health.connect.datatypes.MealType
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import androidx.datastore.dataStoreFile
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferencesSerializer
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.yusufguler.foody.util.Constants.DEFAULT_DIET_TYPE
import com.yusufguler.foody.util.Constants.DEFAULT_MEAL_TYPE
import com.yusufguler.foody.util.Constants.PREFERENCES_DIET_TYPE
import com.yusufguler.foody.util.Constants.PREFERENCES_DIET_TYPE_ID
import com.yusufguler.foody.util.Constants.PREFERENCES_MEAL_TYPE
import com.yusufguler.foody.util.Constants.PREFERENCES_MEAL_TYPE_ID
import com.yusufguler.foody.util.Constants.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)
@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context
)
 {

     private object PreferencesKeys{
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
    }
     private val dataStore: DataStore<Preferences> = context.dataStore
     suspend fun saveMealAndDietType(
         mealType: String,
         mealTypeId:Int,
         dietType: String,
         dietTypeId:Int
     ){
         dataStore.edit {preferences->
             preferences[PreferencesKeys.selectedMealType] = mealType
             preferences[PreferencesKeys.selectedMealTypeId] = mealTypeId
             preferences[PreferencesKeys.selectedDietType] = dietType
             preferences[PreferencesKeys.selectedDietTypeId] = dietTypeId
         }
     }
     val readMealAndDietType : Flow<MealAndDietType> = dataStore.data
         .catch {exception->
             if(exception is IOException){
                 emit(emptyPreferences())
             }else{
                 throw exception
             }
         }
         .map {preferences->
             val selectedMealType = preferences[PreferencesKeys.selectedMealType] ?: DEFAULT_MEAL_TYPE
             val selectedMealTypeId = preferences[PreferencesKeys.selectedMealTypeId] ?: 0
             val selectedDietType = preferences[PreferencesKeys.selectedDietType] ?: DEFAULT_DIET_TYPE
             val selectedDietTypeId = preferences[PreferencesKeys.selectedDietTypeId] ?: 0
             MealAndDietType(
                 selectedMealType,
                 selectedMealTypeId,
                 selectedDietType,
                 selectedDietTypeId
             )
         }
}
data class MealAndDietType(
    val selectedMealType : String,
    val selectedMealTypeId: Int,
    val selectedDietType:String,
    val selectedDietTypeId:Int
)