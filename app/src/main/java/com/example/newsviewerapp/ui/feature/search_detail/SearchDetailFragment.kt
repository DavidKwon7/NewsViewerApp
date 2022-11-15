package com.example.newsviewerapp.ui.feature.search_detail

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.newsviewerapp.R
import com.example.newsviewerapp.databinding.FragmentSearchDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment : Fragment() {

    //todo navArg 설정 후 데이터 넘겨준 후, UI에 띄워주기 [Article]
    //private val args by navArgs<SearchDetailFragmentArgs>()

    val searchDetailViewModel: SearchDetailViewModel by viewModels()
    val args: SearchDetailFragmentArgs by navArgs()
    //val searchList = args.searchData.toString()

    lateinit var binding: FragmentSearchDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_search_detail, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("전송 확인", "onViewCreated: ${args.searchDataList?.title}")
        Toast.makeText(requireContext(), "${args.searchDataList?.url}", Toast.LENGTH_LONG).show()
        //binding.args = args.searchDataList
        binding.searchArticleData = args.searchDataList

        initToolbar()

        //binding.searchArticleData = args.searchDataList
        //binding.textTest.text = args.searchDataList?.url
    }

    private fun initToolbar() {
        binding.tbIcon.setOnClickListener {
            Toast.makeText(requireContext(), "아이콘 클릭", Toast.LENGTH_SHORT).show()
            alertDialog()
        }
    }

    private fun alertDialog() {
        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Local DB")
            .setMessage("선택하신 뉴스를 추가하시겠습니까?")
            .setPositiveButton("확인",
            DialogInterface.OnClickListener { dialogInterface, which ->
                Toast.makeText(requireContext(), "추가 완료", Toast.LENGTH_SHORT).show()
                searchDetailViewModel.insertNews(args.searchDataList)
            })
            .setNegativeButton("취소",
            DialogInterface.OnClickListener { dialogInterface, which ->
                //
            })
        alertDialog.show()
    }
}