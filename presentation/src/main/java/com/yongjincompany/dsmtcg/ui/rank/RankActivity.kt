package com.yongjincompany.dsmtcg.ui.rank

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityRankBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.rank.adapter.RankAdapter
import com.yongjincompany.dsmtcg.viewmodel.rank.RankViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RankActivity : BaseActivity<ActivityRankBinding>(
    R.layout.activity_rank
) {
    private val vm: RankViewModel by viewModels()

    private val myRankData = arrayListOf<FetchRankEntity.Rank>()
    private lateinit var rAdapter: RankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.fetchAllRankValue()

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: RankViewModel.Event) = when (event) {
        is RankViewModel.Event.FetchAllRank -> {

        }

        is RankViewModel.Event.ErrorMessage -> {
            showShortToast(event.message)
        }
    }

    override fun initView() {
        setAdapter()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setMyRankListData(list: FetchRankEntity) {
        myRankData.clear()

        for (i: Int in list.rankList.indices) {
            myRankData.add(list.rankList[i])
        }

        binding.rankList.adapter?.notifyDataSetChanged()
    }

    private fun setAdapter() {
        rAdapter = RankAdapter(myRankData)

        binding.rankList.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL)
            setHasFixedSize(true)
            adapter = rAdapter
        }
    }
}