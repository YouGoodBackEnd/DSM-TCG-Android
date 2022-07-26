package com.yongjincompany.dsmtcg.ui.login

import android.content.Intent
import android.os.Bundle
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityLoginBinding
import com.yongjincompany.dsmtcg.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btLogin.setOnClickListener {
            val accountId: String = binding.etId.text.toString()
            val password: String = binding.etPassword.text.toString()


        }

        binding.ibBack.setOnClickListener {
            finish()
        }
        binding.btLogin.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    override fun initView() {

    }
}