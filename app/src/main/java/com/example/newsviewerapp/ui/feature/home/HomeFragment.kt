package com.example.newsviewerapp.ui.feature.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import com.example.newsviewerapp.R
import com.example.newsviewerapp.databinding.FragmentHomeBinding
import com.example.newsviewerapp.ui.feature.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter()
    }

    private val homeViewModel: HomeViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        binding.btnSearch.setOnClickListener {
            val et = binding.etSearch.text.toString()
            lifecycleScope.launch(Dispatchers.Main) {
                searchNews(et)
                observeHomeList()
            }
        }
        observeHomeList()
    }

    fun initRecyclerView() {
        binding.rvSearch.apply {
            adapter = homeAdapter
        }
    }

    fun searchNews(q: String) {
        homeViewModel.searchArticle(q)
    }

    private fun observeHomeList() {
        lifecycleScope.launch(Dispatchers.Main) {
            /*val et = binding.etSearch.text.toString()
                    homeViewModel.searchNews(et)
                        .collectLatest {
                            homeAdapter.submitData(it)
                        }*/
            homeViewModel.stateFlow.collect { state ->
                when (state) {
                    is SearchState.Empty -> {
                        /*Snackbar.make(
                            binding.root, "화면이 비었습니다", Snackbar.LENGTH_LONG
                        ).show()*/
                        Toast.makeText(requireContext(), "화면이 비었습니다", Toast.LENGTH_SHORT).show()
                        binding.tvEmpty.isVisible = true
                    }

                    is SearchState.Loading -> {
                        Toast.makeText(requireContext(), "loading..", Toast.LENGTH_SHORT).show()
                        binding.pb.isVisible = true
                        binding.rvSearch.isVisible = false
                        binding.tvEmpty.isVisible = false
                    }

                    is SearchState.Success -> {
                        binding.pb.isVisible = false
                        binding.rvSearch.isVisible = true
                        binding.tvEmpty.isVisible = false
                        state.data.collect {
                            homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            binding.rvSearch.adapter = homeAdapter
                        }
                    }
                    is SearchState.Failed -> {
                        Log.e(TAG, "에러 발생: ${state.message}",)
                    }
                    else -> {
                        // nothing
                    }
                }
            }
            /*is SearchState.Empty -> {
                            Snackbar.make(
                                binding.root,"화면이 비었습니다", Snackbar.LENGTH_LONG
                            ).show()
                        }

                        is SearchState.Loading -> {
                            Toast.makeText(requireContext(), "loading..", Toast.LENGTH_SHORT).show()
                            binding.pb.isVisible = true
                            binding.rvSearch.isVisible = false
                        }

                        is SearchState.Success -> {
                            binding.pb.isVisible = false
                            binding.rvSearch.isVisible = true
                            *//*state.data.collect{
                                homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                                binding.rvSearch.adapter = homeAdapter
                            }*//*
                            homeViewModel.searchStateFlow.collect {
                                homeAdapter.submitData(viewLifecycleOwner.lifecycle, it)
                            }
                        }
                        is SearchState.Failed -> {
                            Log.e(TAG, "observeHomeList: ${state.message}" )
                        }*/
        }

    }
    companion object {
        const val TAG = "HomeFragment"
    }
}
