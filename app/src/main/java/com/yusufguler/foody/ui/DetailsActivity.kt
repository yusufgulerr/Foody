package com.yusufguler.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavArgs
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yusufguler.foody.R
import com.yusufguler.foody.adapters.PagerAdapter
import com.yusufguler.foody.data.database.entities.FavoritesEntity
import com.yusufguler.foody.databinding.ActivityDetailsBinding
import com.yusufguler.foody.ui.fragments.ingredients.IngredientsFragment
import com.yusufguler.foody.ui.fragments.instructions.InstructionsFragment
import com.yusufguler.foody.ui.fragments.overview.OverviewFragment
import com.yusufguler.foody.util.Constants.RECIPE_RESULT_KEY
import com.yusufguler.foody.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel : MainViewModel by viewModels()

    private var recipeSaved = false
    private var savedRecipeId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientsFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredients")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )
        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.details_menu,menu)

        val menuItem = menu?.findItem(R.id.save_to_favorites_menu)
        checkSavedRecipes(menuItem!!)

        return true
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }else if(item.itemId == R.id.save_to_favorites_menu && !recipeSaved){
            saveToFavorites(item)
        }else if(item.itemId == R.id.save_to_favorites_menu && recipeSaved){
            removeFromFavorites(item)
        }

        return super.onOptionsItemSelected(item)
    }
    private fun checkSavedRecipes(menuItem: MenuItem) {
        mainViewModel.readFavoriteRecipes.observe(this) {favoritesEntitiy ->
            try {
                for (savedRecipe in favoritesEntitiy){
                    if(savedRecipe.result.id == args.result.id){
                        changeMenuItemColor(menuItem,R.color.yellow)
                        savedRecipeId = savedRecipe.id
                    }else{
                        changeMenuItemColor(menuItem,R.color.white)
                    }
                }
            }catch (e:Exception){
                Log.d("DetailsActivity",e.localizedMessage.toString())
            }

        }
    }

    private fun saveToFavorites(menuItem: MenuItem) {
        val favoritesEntity =
            FavoritesEntity(0,
            args.result
                )
        mainViewModel.insertFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(menuItem,R.color.yellow)
        showSnackBar("Recipe Saved.")
        recipeSaved = true
    }
    private fun removeFromFavorites(item:MenuItem){
        val favoritesEntity =
            FavoritesEntity(
                savedRecipeId,
                args.result,
            )
        mainViewModel.deleteFavoriteRecipe(favoritesEntity)
        changeMenuItemColor(item ,R.color.white)
        showSnackBar("Removed From Favorites")
        recipeSaved = false
    }
    private fun showSnackBar(s: String) {
        Snackbar.make(
            binding.detailsLayout,
            s,
            Snackbar.LENGTH_SHORT
        ).setAction("Okay"){}
            .show()
    }

    private fun changeMenuItemColor(menuItem: MenuItem, color: Int) {
        menuItem.icon?.setTint(ContextCompat.getColor(this,color))
    }
}

