package com.yongjincompany.dsmtcg.ui.shop.cardpack

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.domain.entity.chests.GoldChestOpenEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityGoldChestBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.ui.home.profile.MyPageActivity
import com.yongjincompany.dsmtcg.ui.shop.cardpack.adapter.GoldChestCardAdapter
import com.yongjincompany.dsmtcg.viewmodel.chest.GoldChestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GoldChestActivity : BaseActivity<ActivityGoldChestBinding>(
    R.layout.activity_gold_chest
) {
    private val vm: GoldChestViewModel by viewModels()

    private val myCardData = arrayListOf<GoldChestOpenEntity.Card>()
    private lateinit var rAdapter: GoldChestCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.openGoldChestValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: GoldChestViewModel.Event) = when (event) {
        is GoldChestViewModel.Event.OpenGoldChest -> {
            setMyCardListData(event.goldChestOpenEntity)
            setOpenGoldChest(event.goldChestOpenEntity)
        }

        is GoldChestViewModel.Event.ErrorMessage -> {
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
    private fun setMyCardListData(list: GoldChestOpenEntity) {
        myCardData.clear()

        for (i: Int in list.cardList.indices) {
            myCardData.add(list.cardList[i])
        }

        binding.rvCardList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = GoldChestCardAdapter(myCardData)

        binding.rvCardList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }

    private fun setOpenGoldChest(goldChestData: GoldChestOpenEntity) {
        binding.tvCoin.text = goldChestData.coin.toString()
        binding.tvDiamond.text = goldChestData.diamond.toString()
    }
}