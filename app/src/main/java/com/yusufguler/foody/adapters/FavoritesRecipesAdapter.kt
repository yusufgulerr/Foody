package com.yusufguler.foody.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yusufguler.foody.data.database.entities.FavoritesEntity
import com.yusufguler.foody.databinding.FavoriteRecipesRowLayoutBinding
import com.yusufguler.foody.util.RecipesDiffUtil
import com.yusufguler.foody.viewmodels.MainViewModel

class FavoritesRecipesAdapter(private val requireActivity: FragmentActivity,
                              private val mainViewModel: MainViewModel
) : RecyclerView.Adapter<FavoritesRecipesAdapter.MyViewHolder>() {

    private var favoriteRecipes = emptyList<FavoritesEntity>()

    class MyViewHolder(private val binding: FavoriteRecipesRowLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(favoritesEntity: FavoritesEntity){
            binding.favoriteEntity = favoritesEntity
            binding.executePendingBindings()
        }
        companion object{
            fun from(parent:ViewGroup):MyViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipesRowLayoutBinding.inflate(layoutInflater,parent,false)
                return MyViewHolder(binding)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
      return MyViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val selectedRecipe = favoriteRecipes[position]
        holder.bind(selectedRecipe)
    }

    fun setData(newFavoriteRecipes : List<FavoritesEntity>){
        val favoriteRecipesDiffUtil =
            RecipesDiffUtil(favoriteRecipes,newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

}