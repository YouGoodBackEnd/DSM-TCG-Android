package com.yongjincompany.dsmtcg.ui.chest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.domain.entity.chests.SpecialChestOpenEntity
import com.yongjincompany.dsmtcg.databinding.ItemChestBinding
import com.yongjincompany.dsmtcg.extensions.loadFromUrl

class SpecialChestCardAdapter(
    private val dataList: ArrayList<SpecialChestOpenEntity.Card>,
) : RecyclerView.Adapter<SpecialChestCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]

        holder.bind(item)
    }

    class ViewHolder private constructor(
        val binding: ItemChestBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: SpecialChestOpenEntity.Card) {

            binding.cardName.text = item.cardName
            binding.cardGrade.text = item.grade
            item.cardImageUrl.let { binding.ivCard.loadFromUrl(it) }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemChestBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = dataList.size
}