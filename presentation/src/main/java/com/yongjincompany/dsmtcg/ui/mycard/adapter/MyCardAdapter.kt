package com.yongjincompany.dsmtcg.ui.mycard.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gun0912.tedpermission.provider.TedPermissionProvider.context
import com.yongjincompany.domain.entity.cards.FetchMyCardEntity
import com.yongjincompany.dsmtcg.databinding.ItemMyCardBinding
import com.yongjincompany.dsmtcg.extensions.loadFromUrl
import com.yongjincompany.dsmtcg.ui.mycard.CardDetailActivity
import kotlin.collections.ArrayList

class MyCardAdapter(
    private val dataList: ArrayList<FetchMyCardEntity.Card>,
) : RecyclerView.Adapter<MyCardAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = dataList[position]

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
            itemView.setOnClickListener {
                val intent = Intent(context, CardDetailActivity::class.java).apply {
                    putExtra("NAME", item.name)
                    putExtra("GRADE", item.grade)
                    putExtra("CARD_IMAGE", item.cardImageUrl)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
            }
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