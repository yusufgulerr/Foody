package com.yusufguler.foody.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.yusufguler.foody.R
import com.yusufguler.foody.adapters.IngredientsAdapter
import com.yusufguler.foody.databinding.FragmentIngredientsBinding
import com.yusufguler.foody.databinding.FragmentRecipesBinding
import com.yusufguler.foody.models.Result
import com.yusufguler.foody.util.Constants.RECIPE_RESULT_KEY


class IngredientsFragment : Fragment() {
    private var _binding: FragmentIngredientsBinding? = null

    private val binding get() = _binding!!

    private val mAdapter : IngredientsAdapter by lazy{IngredientsAdapter()}
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentIngredientsBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)
        setupRecyclerView(binding)

        myBundle?.extendedIngredients?.let{
            mAdapter.setData(it)
        }

        return  binding.root
    }

    private fun setupRecyclerView(binding:FragmentIngredientsBinding){
        binding.ingredientsRecyclerview.adapter = mAdapter
        binding.ingredientsRecyclerview.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}