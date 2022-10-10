package com.yongjincompany.dsmtcg.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.domain.entity.chests.FetchSpecialChestTimeEntity
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.databinding.FragmentHomeBinding
import com.yongjincompany.dsmtcg.extensions.loadCircleFromUrl
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.chest.FreeChestActivity
import com.yongjincompany.dsmtcg.ui.chest.SpecialChestActivity
import com.yongjincompany.dsmtcg.ui.home.profile.MyPageActivity
import com.yongjincompany.dsmtcg.ui.home.setting.SettingActivity
import com.yongjincompany.dsmtcg.ui.rank.RankActivity
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
        vm.fetchFreeChest()
        vm.fetchSpecialChest()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: HomeViewModel.Event) = when (event) {
        is HomeViewModel.Event.FetchMyInfo -> {
            setProfileValue(event.fetchMyInfoEntity)
        }
        is HomeViewModel.Event.FetchFreeChestTime -> {
            setFreeChestTimeValue(event.fetchFreeChestTimeEntity)
        }
        is HomeViewModel.Event.FetchSpecialChestTime -> {
            setSpecialChestTimeValue(event.fetchSpecialChestTimeEntity)
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
        binding.menu.setOnClickListener {
            val intent = Intent(context, SettingActivity::class.java)
            startActivity(intent)
        }
        binding.freeCardpack.setOnClickListener {
            if (binding.tvFreeChest.text == "오픈 가능") {
                val intent = Intent(context, FreeChestActivity::class.java)
                startActivity(intent)
            } else {
                showShortToast("아직 오픈 가능한 시간이 아닙니다")
            }
        }
        binding.specialCardpack.setOnClickListener {
            if (binding.tvSpecialChest.text == "오픈 가능") {
                val intent = Intent(context, SpecialChestActivity::class.java)
                startActivity(intent)
            } else {
                showShortToast("아직 오픈 가능한 시간이 아닙니다")
            }
        }
        binding.ivRank.setOnClickListener {
            val intent = Intent(context, RankActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setProfileValue(profileData: FetchMyInfoEntity) {
        binding.tvName.text = profileData.name
        val tvRank = binding.tvRank
        if (tvRank.text == "0") tvRank.text = profileData.rank.toString() else tvRank.text =
            "랭크 순위권 외"
        profileData.profileImageUrl.let { binding.ivProfileImage.loadCircleFromUrl(it) }
        binding.tvBambooCount.text = profileData.coin.toString()
    }

    private fun setFreeChestTimeValue(freeChestTimeData: FetchFreeChestTimeEntity) {
        val tvFreeChest = binding.tvFreeChest
        if (freeChestTimeData.isOpened) tvFreeChest.text = "오픈 가능" else tvFreeChest.text = "안열림ㅅㄱ"
    }

    private fun setSpecialChestTimeValue(specialChestTimeData: FetchSpecialChestTimeEntity) {
        val tvSpecialChest = binding.tvSpecialChest
        if (specialChestTimeData.isOpened) tvSpecialChest.text = "오픈 가능" else tvSpecialChest.text =
            "안열림ㅅㄱ"
    }
}