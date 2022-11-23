package com.example.newsviewerapp.ui.feature.category

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsviewerapp.R
import com.example.newsviewerapp.databinding.FragmentCategoryBinding
import com.example.newsviewerapp.ui.feature.base.BaseFragment
import com.example.newsviewerapp.ui.feature.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter(
            itemClickListener = {
                Toast.makeText(requireContext(), getString(R.string.click), Toast.LENGTH_SHORT)
                    .show()
            }
        )
    }

    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        searchNews()
        observeCategoryList()
        clickRadioButton()

    }

    private fun initRecyclerView() {
        binding.rvCategory.apply {
            adapter = categoryAdapter
        }
    }

    private fun searchNews() {
        categoryViewModel.categoryArticle("us")
    }

    private fun observeCategoryList() {
        lifecycleScope.launch(Dispatchers.Main) {
            categoryViewModel.categoryStateFlow.collect { state ->
                when (state) {
                    is CategoryState.Empty -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.empty_screen),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.tvEmpty.isVisible = true
                    }
                    is CategoryState.Loading -> {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.loading),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.pb.isVisible = true
                        binding.rvCategory.isVisible = false
                        binding.tvEmpty.isVisible = false
                    }
                    is CategoryState.Success -> {
                        binding.pb.isVisible = false
                        binding.rvCategory.isVisible = true
                        binding.tvEmpty.isVisible = false
                        state.data.collect {
                            categoryAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            binding.rvCategory.adapter = categoryAdapter
                        }
                    }
                    is CategoryState.Failed -> {
                        Log.e(SearchFragment.TAG, "에러 발생: ${state.message}")
                    }
                }
            }
        }
    }

    private fun clickRadioButton() {
        binding.radioGroup.setOnCheckedChangeListener() { group, checkId ->
            when (checkId) {
                R.id.radio_btn_us -> {
                    categoryViewModel.categoryArticle("us")
                }
                R.id.radio_btn_gb -> {
                    categoryViewModel.categoryArticle("gb")
                }
                R.id.radio_btn_jp -> {
                    categoryViewModel.categoryArticle("jp")
                }
                R.id.radio_btn_kr -> {
                    categoryViewModel.categoryArticle("kr")
                }
                R.id.radio_btn_cn -> {
                    categoryViewModel.categoryArticle("cn")
                }
            }
        }
    }


    companion object {
        const val TAG = "CategoryFragment"
    }
}