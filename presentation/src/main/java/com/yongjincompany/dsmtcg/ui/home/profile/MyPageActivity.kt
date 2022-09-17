package com.yongjincompany.dsmtcg.ui.home.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.databinding.ActivityMyPageBinding
import com.yongjincompany.dsmtcg.extensions.loadCircleFromUrl
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : BaseActivity<ActivityMyPageBinding>(
    R.layout.activity_my_page
) {
    private val vm : HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchMyInfoValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

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
        binding.exit.setOnClickListener {
            finish()
        }
    }

    private fun setProfileValue(profileData: FetchMyInfoEntity) {
        binding.a.text = profileData.cardCount.agradeCardCount.toString()
        binding.b.text = profileData.cardCount.bgradeCardCount.toString()
        binding.c.text = profileData.cardCount.cgradeCardCount.toString()
        binding.s.text = profileData.cardCount.sgradeCardCount.toString()
        binding.ss.text = profileData.cardCount.ssgradeCardCount.toString()
        binding.playerName.text = profileData.name
        val tvRank = binding.rank
        if (tvRank.text == "0") tvRank.text = profileData.rank.toString() else tvRank.text = "랭크 순위권 외"
        profileData.profileImageUrl.let { binding.ivProfile.loadCircleFromUrl(it) }
    }

}
