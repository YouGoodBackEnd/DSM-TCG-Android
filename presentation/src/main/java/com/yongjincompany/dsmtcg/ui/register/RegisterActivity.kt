package com.yongjincompany.dsmtcg.ui.register

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityRegisterBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.viewmodel.register.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(
    R.layout.activity_register
) {
    private val vm: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.ibBack.setOnClickListener {
            finish()
        }

        binding.btContinue.setOnClickListener {
            val accountId: String = binding.etId.text.toString()
            val password: String = binding.etPassword.text.toString()
            val name: String = binding.etName.text.toString()
            vm.register(accountId, password, name)
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: RegisterViewModel.Event) = when (event) {
        is RegisterViewModel.Event.SuccessRegister -> {
            val intent = Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)

            showShortToast("회원가입을 성공적으로 완료하였습니다!")
        }

        is RegisterViewModel.Event.ErrorMessage -> showShortToast(event.message)

        is RegisterViewModel.Event.BadRequest -> showShortToast("비밀번호에는 특수문자가 포함되어야합니다.")

        is RegisterViewModel.Event.Conflict -> showShortToast("이미 회원가입이 완료된 전화번호입니다.")

        is RegisterViewModel.Event.UnKnown -> showShortToast("알 수 없는 오류가 발생하였습니다.")
    }

    override fun initView() {

    }

}