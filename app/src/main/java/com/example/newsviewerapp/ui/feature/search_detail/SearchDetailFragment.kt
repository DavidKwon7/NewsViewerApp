package com.example.newsviewerapp.ui.feature.search_detail

import android.os.Bundle
import android.util.Log
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
import com.example.newsviewerapp.databinding.FragmentSearchDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    val searchDetailViewModel: SearchDetailViewModel by viewModels()
    val args: SearchDetailFragmentArgs by navArgs()

    lateinit var binding: FragmentSearchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_search_detail, container, false
        )
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("전송 확인", "onViewCreated: ${args.searchDataList?.title}")
        Toast.makeText(requireContext(), "${args.searchDataList?.url}", Toast.LENGTH_LONG).show()
        binding.searchArticleData = args.searchDataList

        initToolbar()

    }

    private fun initToolbar() {
        binding.tbIcon.setOnClickListener {
            Toast.makeText(requireContext(), getString(R.string.icon_click), Toast.LENGTH_SHORT).show()
            alertDialog()
        }
    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.local_db))
            .setMessage(getString(R.string.ask_news_add))
            .setPositiveButton(getString(R.string.check)) { _, _ ->
                Toast.makeText(requireContext(), getString(R.string.finish_add), Toast.LENGTH_SHORT).show()
                searchDetailViewModel.insertNews(args.searchDataList)
            }
            .setNegativeButton(getString(R.string.cancel)) { _, _ ->

            }
        alertDialog.show()
    }
}