package com.yongjincompany.dsmtcg.ui.mycard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.dsmtcg.databinding.ItemMyCardBinding
import com.yongjincompany.dsmtcg.extensions.loadFromUrl
import kotlin.collections.ArrayList

class MyCardAdapter(
    private val dataList: ArrayList<FetchMyCardEntity.Card>,
) : RecyclerView.Adapter<MyCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]

        holder.itemView.setOnClickListener {

        }

        holder.bind(item)
    }

    class ViewHolder private constructor(
        val binding: ItemMyCardBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: FetchMyCardEntity.Card) {

            binding.cardName.text = item.name
            binding.cardCount.text = item.count.toString()
            binding.cardGrade.text = item.grade
            item.cardImageUrl.let { binding.ivCard.loadFromUrl(it) }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemMyCardBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun getItemCount() = dataList.size
}