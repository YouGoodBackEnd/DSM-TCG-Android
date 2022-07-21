package com.yongjincompany.dsmtcg.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start)
    }
}