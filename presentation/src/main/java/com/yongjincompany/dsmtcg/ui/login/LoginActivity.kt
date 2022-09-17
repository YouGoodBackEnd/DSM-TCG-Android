package com.yongjincompany.dsmtcg.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityLoginBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.viewmodel.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {

    private val vm: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        binding.btnLogin.setOnClickListener {
            loginToast()
        }

        binding.btnLogin.setOnClickListener {
            val accountId: String = binding.etId.text.toString()
            val password: String = binding.etPassword.text.toString()

            vm.postLogin(accountId, password)
        }

        binding.ivBack.setOnClickListener {
            finish()
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: LoginViewModel.Event) = when (event) {
        is LoginViewModel.Event.LoginSuccess -> {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        is LoginViewModel.Event.NoInternet -> showShortToast("인터넷 연결을 확인해주세요.")

        is LoginViewModel.Event.BadRequest -> showShortToast("잘못된 요청입니다. 요청한 값을 확인해주세요.")

        is LoginViewModel.Event.WrongAccount -> showShortToast("아이디나 비밀번호가 틀립니다.")

        else -> showShortToast("알 수 없는 에러가 발생하였습니다.")
    }

    private fun loginToast() {
        val id = binding.etId.text
        val password = binding.etPassword.text
        if (id.isEmpty()) {
            Toast.makeText(this, "아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
        } else if (id.isNotEmpty() && password.isEmpty()) {
            Toast.makeText(this, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else if (id.isNotEmpty() && password.isNotEmpty()) {

        }
    }

    override fun initView() {

    }
}