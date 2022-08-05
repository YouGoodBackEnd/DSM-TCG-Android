package com.yongjincompany.dsmtcg.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.databinding.FragmentHomeBinding
import com.yongjincompany.dsmtcg.ui.MyPageActivity


class  HomeFragment: BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun initView() {
        binding.userCardview.setOnClickListener {
            val intent = Intent(context, MyPageActivity::class.java)
            startActivity(intent)
        }
    }
}