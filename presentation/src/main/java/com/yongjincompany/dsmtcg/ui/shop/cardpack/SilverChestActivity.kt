package com.yongjincompany.dsmtcg.ui.shop.cardpack

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.domain.entity.chests.SilverChestOpenEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivitySilverChestBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.ui.home.profile.MyPageActivity
import com.yongjincompany.dsmtcg.ui.shop.cardpack.adapter.SilverChestCardAdapter
import com.yongjincompany.dsmtcg.viewmodel.chest.SilverChestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SilverChestActivity : BaseActivity<ActivitySilverChestBinding>(
    R.layout.activity_silver_chest
) {
    private val vm: SilverChestViewModel by viewModels()

    private val myCardData = arrayListOf<SilverChestOpenEntity.Card>()
    private lateinit var rAdapter: SilverChestCardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.openSilverChestValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: SilverChestViewModel.Event) = when (event) {
        is SilverChestViewModel.Event.OpenSilverChest -> {
            setMyCardListData(event.silverChestOpenEntity)
            setOpenSilverChest(event.silverChestOpenEntity)
        }

        is SilverChestViewModel.Event.ErrorMessage -> {
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
    private fun setMyCardListData(list: SilverChestOpenEntity) {
        myCardData.clear()

        for (i: Int in list.cardList.indices) {
            myCardData.add(list.cardList[i])
        }

        binding.rvCardList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = SilverChestCardAdapter(myCardData)

        binding.rvCardList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }

    private fun setOpenSilverChest(silverChestData: SilverChestOpenEntity) {
        binding.tvCoin.text = silverChestData.coin.toString()
        binding.tvDiamond.text = silverChestData.diamond.toString()
    }
}