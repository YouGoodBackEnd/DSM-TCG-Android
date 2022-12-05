package com.yongjincompany.dsmtcg.ui.shop.cardpack

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.domain.entity.chests.LegendChestOpenEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityLegendChestBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.ui.home.profile.MyPageActivity
import com.yongjincompany.dsmtcg.ui.shop.cardpack.adapter.LegendChestCardAdapter
import com.yongjincompany.dsmtcg.viewmodel.chest.LegendChestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LegendChestActivity : BaseActivity<ActivityLegendChestBinding>(
    R.layout.activity_legend_chest
) {
    private val vm: LegendChestViewModel by viewModels()

    private val myCardData = arrayListOf<LegendChestOpenEntity.Card>()
    private lateinit var rAdapter: LegendChestCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.openLegendChestValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: LegendChestViewModel.Event) = when (event) {
        is LegendChestViewModel.Event.OpenLegendChest -> {
            setMyCardListData(event.legendChestOpenEntity)
            setOpenLegendChest(event.legendChestOpenEntity)
        }

        is LegendChestViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        Glide.with(this).load(R.drawable.chest_back).centerCrop().into(binding.chestBackground)
        setAdapter()

        binding.chestBackground.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onBackPressed() {

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setMyCardListData(list: LegendChestOpenEntity) {
        myCardData.clear()

        for (i: Int in list.cardList.indices) {
            myCardData.add(list.cardList[i])
        }

        binding.rvCardList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = LegendChestCardAdapter(myCardData)

        binding.rvCardList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }

    private fun setOpenLegendChest(legendChestData: LegendChestOpenEntity) {
        binding.tvCoin.text = legendChestData.coin.toString()
        binding.tvDiamond.text = legendChestData.diamond.toString()
    }
}