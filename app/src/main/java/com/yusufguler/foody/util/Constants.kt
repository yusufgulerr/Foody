package com.yusufguler.foody.util

object Constants {
    const val BASE_URL = "https://api.spoonacular.com"

    //API QUERY KEYS
    const val QUERY_SEARCH = "query"
    const val QUERY_NUMBER = "number"
    const val QUERY_APIKEY = "apiKey"
    const val QUERY_TYPE = "type"
    const val QUERY_DIET = "diet"
    const val QUERY_ADD_RECIPE_INFORMATION = "addRecipeInformation"
    const val QUERY_FILL_INGREDIENTS = "fillIngredients"

    //Room Database
    const val DATABASE_NAME = "recipes_database"
    const val RECIPES_TABLE = "recipes_table"

    //Bottom Sheet and Preferences
    const val DEFAULT_RECIPES_NUMBER = "50"
    const val DEFAULT_MEAL_TYPE = "main course"
    const val DEFAULT_DIET_TYPE = "gluten free"

    const val PREFERENCES_NAME = "foody_preferences"
    const val PREFERENCES_MEAL_TYPE = "mealType"
    const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
    const val PREFERENCES_DIET_TYPE = "dietType"
    const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"
    const val PREFERENCES_BACK_ONLINE = "backOnline"

}