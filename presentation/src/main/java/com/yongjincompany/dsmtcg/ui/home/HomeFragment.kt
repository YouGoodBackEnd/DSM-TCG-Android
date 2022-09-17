package com.yongjincompany.dsmtcg.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.databinding.FragmentHomeBinding
import com.yongjincompany.dsmtcg.extensions.loadCircleFromUrl
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.home.profile.MyPageActivity
import com.yongjincompany.dsmtcg.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(
    R.layout.fragment_home
) {
    private val vm: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        vm.fetchMyInfoValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: HomeViewModel.Event) = when (event) {
        is HomeViewModel.Event.FetchMyInfo -> {
            setProfileValue(event.fetchMyInfoEntity)
        }
        is HomeViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }

    }

    override fun initView() {
        binding.userCardview.setOnClickListener {
            val intent = Intent(context, MyPageActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setProfileValue(profileData: FetchMyInfoEntity) {
        binding.name.text = profileData.name
        val tvRank = binding.tvRank
        if (tvRank.text == "0") tvRank.text = profileData.rank.toString() else tvRank.text =
            "랭크 순위권 외"
        profileData.profileImageUrl.let { binding.myImage.loadCircleFromUrl(it) }
    }
}