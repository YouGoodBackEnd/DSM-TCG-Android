package com.yongjincompany.dsmtcg.ui.mycard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.entity.chests.FetchFreeChestTimeEntity
import com.yongjincompany.dsmtcg.base.BaseFragment
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.databinding.FragmentMyCardBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.mycard.adapter.MyCardAdapter
import com.yongjincompany.dsmtcg.viewmodel.mycard.MyCardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyCardFragment : BaseFragment<FragmentMyCardBinding>(
    R.layout.fragment_my_card
) {

    private val vm: MyCardViewModel by viewModels()

    private val myCardData = arrayListOf<FetchMyCardEntity.Card>()
    private lateinit var rAdapter: MyCardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        vm.fetchMyCardValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    private fun handleEvent(event: MyCardViewModel.Event) = when (event) {

        is MyCardViewModel.Event.FetchMyCard -> {

            when {
                event.fetchMyCardEntity.cardList.isEmpty() -> {
                    binding.tvNullList.visibility = View.VISIBLE
                }

                event.fetchMyCardEntity.cardList.isNotEmpty() -> {
                    setMyCardListData(event.fetchMyCardEntity)
                    binding.tvNullList.visibility = View.GONE
                }
                else -> {}
            }
            setCollectionValue(event.fetchMyCardEntity)
        }
        is MyCardViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }
    override fun initView() {
        setAdapter()
    }

    private fun setCollectionValue(myCardData: FetchMyCardEntity) {
        binding.tvCollection.text = "${myCardData.totalCount.toString()} / 27"
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setMyCardListData(list: FetchMyCardEntity) {
        myCardData.clear()

        for (i: Int in list.cardList.indices) {
            myCardData.add(list.cardList[i])
        }

        binding.cardList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = MyCardAdapter(myCardData)

        binding.cardList.apply {
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }
}