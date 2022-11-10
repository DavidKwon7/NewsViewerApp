package com.example.newsviewerapp.ui.feature.category

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
               Toast.makeText(requireContext(), "클릭", Toast.LENGTH_SHORT).show()
           }
       )
   }

    private val categoryViewModel: CategoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        searchNews()
        observeCategoryList()
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
                when(state) {
                    is CategoryState.Empty -> {
                        Toast.makeText(requireContext(), "화면이 비었습니다", Toast.LENGTH_SHORT).show()
                        binding.tvEmpty.isVisible = true
                    }
                    is CategoryState.Loading -> {
                        Toast.makeText(requireContext(), "로딩 중..", Toast.LENGTH_SHORT).show()
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


    companion object {
        const val TAG = "CategoryFragment"
    }
}