package com.example.newsviewerapp.ui.feature.favorite

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsviewerapp.R
import com.example.newsviewerapp.common.navigateWithArgs
import com.example.newsviewerapp.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter: FavoriteAdapter by lazy {
        FavoriteAdapter(
            itemClickListener = {
                navigateWithArgs(
                    FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteDetailFragment(
                        it
                    )
                )
            }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_favorite, container, false
        )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        getNews()
        observeGetFavoriteNews()
    }

    private fun initRecyclerView() {
        binding.rvList.apply {
            adapter = favoriteAdapter
        }
    }

    private fun getNews() {
        favoriteViewModel.getNews()
    }

    private fun observeGetFavoriteNews() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteViewModel.favoriteStateFlow.collect { state ->
                    when(state) {
                        is FavoriteState.Empty -> {

                        }
                        is FavoriteState.Loading -> {

                        }
                        is FavoriteState.Success -> {
                            val data = state.data
                            favoriteAdapter.submitList(data)
                        }
                        is FavoriteState.Failed -> {

                        }
                    }
                }
            }
        }
    }
}