package com.yongjincompany.dsmtcg.ui.home.setting

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.gun0912.tedpermission.provider.TedPermissionProvider
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivitySettingBinding
import com.yongjincompany.dsmtcg.extensions.repeatOnStarted
import com.yongjincompany.dsmtcg.ui.StartActivity
import com.yongjincompany.dsmtcg.viewmodel.setting.LogOutViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingActivity : BaseActivity<ActivitySettingBinding>(
    R.layout.activity_setting
) {
    private val vm: LogOutViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.logout.setOnClickListener {
            vm.logOut()
        }

        repeatOnStarted {
            vm.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: LogOutViewModel.Event) = when (event) {
        is LogOutViewModel.Event.LogOut -> {
            val intent = Intent(TedPermissionProvider.context, StartActivity::class.java)
            finishAffinity()
            startActivity(intent)
        }

        is LogOutViewModel.Event.LogOutFailed -> showShortToast("로그아웃에 실패하셨습니다")
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