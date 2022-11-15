package com.example.newsviewerapp.ui.feature.favorite_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsviewerapp.R
import com.example.newsviewerapp.databinding.FragmentFavoriteDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteDetailFragment : Fragment() {

    val favoriteDetailViewModel: FavoriteDetailViewModel by viewModels()
    val args: FavoriteDetailFragmentArgs by navArgs()

    lateinit var binding: FragmentFavoriteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_favorite_detail, container, false
        )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.favoriteArticleData = args.favoriteDataList

        initToolbar()
    }

    private fun initToolbar() {
        binding.tbIcon.setOnClickListener {
            alertDialog()
        }
    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Local DB")
            .setMessage("선택하신 뉴스를 삭제하시겠습니까?")
            .setPositiveButton("확인"
            ) { _, _ ->
                Toast.makeText(requireContext(), "삭제 완료", Toast.LENGTH_SHORT).show()
                favoriteDetailViewModel.deleteNews(args.favoriteDataList)
            }
            .setNegativeButton("취소"
            ) { _, _ ->
                // nothing
            }
        alertDialog.show()
    }
}