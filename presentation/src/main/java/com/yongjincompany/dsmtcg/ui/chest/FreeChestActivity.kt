package com.yongjincompany.dsmtcg.ui.chest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.domain.entity.chests.FreeChestOpenEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityFreeChestBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.ui.chest.adapter.FreeChestCardAdapter
import com.yongjincompany.dsmtcg.ui.home.HomeFragment
import com.yongjincompany.dsmtcg.viewmodel.chest.FreeChestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FreeChestActivity : BaseActivity<ActivityFreeChestBinding>(
    R.layout.activity_free_chest
) {
    private val vm: FreeChestViewModel by viewModels()

    private val myCardData = arrayListOf<FreeChestOpenEntity.Card>()
    private lateinit var rAdapter: FreeChestCardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.openFreeChestValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: FreeChestViewModel.Event) = when (event) {
        is FreeChestViewModel.Event.OpenFreeChest -> {
            setMyCardListData(event.freeChestOpenEntity)
            setOpenFreeChest(event.freeChestOpenEntity)
        }

        is FreeChestViewModel.Event.ErrorMessage -> {
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
    private fun setMyCardListData(list: FreeChestOpenEntity) {
        myCardData.clear()

        for (i: Int in list.cardList.indices) {
            myCardData.add(list.cardList[i])
        }

        binding.rvCardList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = FreeChestCardAdapter(myCardData)

        binding.rvCardList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }

    private fun setOpenFreeChest(freeChestData: FreeChestOpenEntity) {
        binding.tvCoin.text = freeChestData.coin.toString()
        binding.tvDiamond.text = freeChestData.diamond.toString()
    }
}