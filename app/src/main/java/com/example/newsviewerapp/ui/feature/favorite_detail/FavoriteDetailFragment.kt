package com.example.newsviewerapp.ui.feature.favorite_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.newsviewerapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteDetailFragment : Fragment() {

    val favoriteDetailViewModel: FavoriteDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_detail, container, false)
    }
}