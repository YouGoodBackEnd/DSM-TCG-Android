package com.yongjincompany.dsmtcg.ui.register

import android.os.Bundle
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ibBack.setOnClickListener {
            finish()
        }
    }

    override fun initView() {

    }

}