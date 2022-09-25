package com.yongjincompany.dsmtcg.ui.home.setting

import android.content.Intent
import android.os.Bundle
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivitySettingBinding

class SettingActivity : BaseActivity<ActivitySettingBinding>(
    R.layout.activity_setting
){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun initView() {
        binding.ivBack.setOnClickListener {
            finish()
        }
        binding.myInfo.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            startActivity(intent)


        }
    }
}