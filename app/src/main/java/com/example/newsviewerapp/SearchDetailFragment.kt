package com.example.newsviewerapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.example.newsviewerapp.databinding.FragmentSearchDetailBinding
import com.example.newsviewerapp.ui.feature.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    //todo navArg 설정 후 데이터 넘겨준 후, UI에 띄워주기 [Article]
    //private val args by navArgs<SearchDetailFragmentArgs>()

    val args: SearchDetailFragmentArgs by navArgs()
    //val searchList = args.searchData.toString()

    lateinit var binding: FragmentSearchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_detail, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("전송 확인", "onViewCreated: ${args.searchDataList?.title}")
        Toast.makeText(requireContext(), "${args.searchDataList?.url}", Toast.LENGTH_LONG).show()
        //binding.args = args.searchDataList
        binding.searchArticleData = args.searchDataList

        //binding.searchArticleData = args.searchDataList
        //binding.textTest.text = args.searchDataList?.url
    }
}