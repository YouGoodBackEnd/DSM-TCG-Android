package com.yongjincompany.dsmtcg.ui.chest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivitySpecialChestBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.chest.adapter.SpecialChestCardAdapter
import com.yongjincompany.dsmtcg.viewmodel.chest.SpecialChestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpecialChestActivity : BaseActivity<ActivitySpecialChestBinding>(
    R.layout.activity_special_chest
) {
    private val vm: SpecialChestViewModel by viewModels()

    private val myCardData = arrayListOf<SpecialChestOpenEntity.Card>()
    private lateinit var rAdapter: SpecialChestCardAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.openSpecialChestValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: SpecialChestViewModel.Event) = when (event) {
        is SpecialChestViewModel.Event.OpenSpecialChest -> {
            setMyCardListData(event.specialChestOpenEntity)
            setOpenSpecialChest(event.specialChestOpenEntity)
        }
        is SpecialChestViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        Glide.with(this).load(R.drawable.chest_back).centerCrop().into(binding.chestBackground)
        setAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setMyCardListData(list: SpecialChestOpenEntity) {
        myCardData.clear()

        for (i: Int in list.cardList.indices) {
            myCardData.add(list.cardList[i])
        }

        binding.rvCardList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = SpecialChestCardAdapter(myCardData)

        binding.rvCardList.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }

    private fun setOpenSpecialChest(SpecialChestData: SpecialChestOpenEntity) {
        binding.tvCoin.text = SpecialChestData.coin.toString()
        binding.tvDiamond.text = SpecialChestData.diamond.toString()
    }
}