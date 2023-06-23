package com.yusufguler.foody.ui.fragments.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yusufguler.foody.R
import com.yusufguler.foody.adapters.FavoritesRecipesAdapter
import com.yusufguler.foody.databinding.FragmentFavoriteRecipesBinding
import com.yusufguler.foody.databinding.FragmentRecipesBinding
import com.yusufguler.foody.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {
    private var _binding: FragmentFavoriteRecipesBinding? = null

    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by viewModels()
    private val mAdapter: FavoritesRecipesAdapter by lazy {
        FavoritesRecipesAdapter(
            requireActivity(),
            mainViewModel
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter


        setupRecyclerView(binding.favoriteRecipesRecyclerView)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipes_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.deleteAllFavoriteRecipesMenu){
            mainViewModel.deleteAllFavoriteRecipes()
            Snackbar.make(binding.root,"All recipes removed.",Snackbar.LENGTH_SHORT).setAction("Okay"){}.show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView){
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        mAdapter.clearContextualActionMode()
    }
}