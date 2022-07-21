package com.yongjincompany.dsmtcg.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.databinding.FragmentMyCardBinding

class MyCardFragment : BaseFragment<FragmentMyCardBinding>(
    R.layout.fragment_my_card
) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initView() {
        binding.cardList.layoutManager = LinearLayoutManager(context)
    }
}