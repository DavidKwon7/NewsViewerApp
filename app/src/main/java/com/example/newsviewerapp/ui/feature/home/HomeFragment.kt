package com.example.newsviewerapp.ui.feature.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.newsviewerapp.R
import com.example.newsviewerapp.databinding.FragmentHomeBinding
import com.example.newsviewerapp.ui.feature.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun observeHomeList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.searchStateFlow.collect { state ->
                    when(state) {
                        is SearchState.Empty -> {
                            Snackbar.make(
                                binding.root,"화면이 비었습니다", Snackbar.LENGTH_LONG
                            ).show()
                        }

                        is SearchState.Loading -> {
                            Toast.makeText(requireContext(), "로딩 중..", Toast.LENGTH_SHORT).show()
                            // pb 넣어주기
                        }

                        is SearchState.Success -> {
                            Log.d(TAG, "observeHomeList: $state")
                        }
                        is SearchState.Failed -> {
                            Log.e(TAG, "observeHomeList: ${state.message}" )
                        }
                    }
                }
            }
        }
    }
    companion object {
        const val TAG = "HomeFragment"
    }
}
