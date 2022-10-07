package com.yongjincompany.dsmtcg.ui.rank.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.domain.entity.chests.FreeChestOpenEntity
import com.yongjincompany.domain.entity.ranks.FetchRankEntity
import com.yongjincompany.dsmtcg.databinding.ItemChestBinding
import com.yongjincompany.dsmtcg.databinding.ItemRankBinding
import com.yongjincompany.dsmtcg.extensions.loadFromUrl

class RankAdapter(
    private val dataList: ArrayList<FetchRankEntity.Rank>,
) : RecyclerView.Adapter<RankAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]

        holder.bind(item)
    }

    class ViewHolder private constructor(
        val binding: ItemRankBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FetchRankEntity.Rank) {

            binding.tvWalkCount.text = item.accountId
            binding.tvName.text = item.name
            item.profileImageUrl.let { binding.ivProfileImage.loadFromUrl(it) }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemRankBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = dataList.size
}