package com.yongjincompany.dsmtcg.util

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

class BindingViewHolder(val binding: ViewDataBinding): RecyclerView.ViewHolder(binding.root){
    fun setLifeCycleOwner(lifecycleOwner: LifecycleOwner) {
        binding.lifecycleOwner = lifecycleOwner
    }
}