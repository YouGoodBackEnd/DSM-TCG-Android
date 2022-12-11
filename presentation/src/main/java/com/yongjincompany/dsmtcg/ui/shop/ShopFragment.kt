package com.yongjincompany.dsmtcg.ui.shop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.yongjincompany.domain.entity.users.FetchMyInfoEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.databinding.FragmentShopBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.shop.cardpack.GoldChestActivity
import com.yongjincompany.dsmtcg.ui.shop.cardpack.LegendChestActivity
import com.yongjincompany.dsmtcg.ui.shop.cardpack.SilverChestActivity
import com.yongjincompany.dsmtcg.viewmodel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding>(
    R.layout.fragment_shop
) {
    private val vm: HomeViewModel by viewModels()
    private lateinit var bambooCount : String

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

        else -> {}
    }

    override fun initView() {


        binding.ddongCard.setOnClickListener {
            if (bambooCount.toInt() < 3000) {
                showShortToast("대나무 토큰이 부족합니다")
            } else {
                println(bambooCount)
                val intent = Intent(context, SilverChestActivity::class.java)
                startActivity(intent)
            }
        }

        binding.roseCard.setOnClickListener {
            if (bambooCount.toInt() < 12000) {
                showShortToast("대나무 토큰이 부족합니다")
            } else {
                println(bambooCount)
                val intent = Intent(context, GoldChestActivity::class.java)
                startActivity(intent)
            }
        }

        binding.legendCard.setOnClickListener {
            if (bambooCount.toInt() < 50000) {
                showShortToast("대나무 토큰이 부족합니다")
            } else {
                val intent = Intent(context, LegendChestActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun setProfileValue(profileData: FetchMyInfoEntity) {
        binding.tvBambooCount.text = profileData.coin.toString()
        bambooCount = profileData.coin.toString()
    }

}