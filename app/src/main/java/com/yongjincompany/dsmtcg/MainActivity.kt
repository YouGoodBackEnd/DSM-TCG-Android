package com.yongjincompany.dsmtcg

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.semicolon.walkhub.ui.base.BaseActivity
import com.yongjincompany.dsmtcg.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun initBottomNav() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.navHostFragmentActivityMain)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }

    override fun initView() {
        initBottomNav()
    }
}