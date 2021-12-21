package com.nativedevps.mlm.main.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.nativedevps.mlm.R
import com.nativedevps.mlm.databinding.ActivityMainsBinding
import com.nativedevps.mlm.main.ui.splash.SplashActivity
import com.nativedevps.support.base_class.ActionBarActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ActionBarActivity<ActivityMainsBinding, MainViewModel>(
    R.layout.activity_mains,
    MainViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        initData()
        initListener()
        initPreview()
    }

    private fun initData() {
        viewModel.userProfile.asLiveData().observe(this, { userProfile ->
            /*if (userProfile.emailId.isNullOrEmpty()) { //todo: optional
                initFreshLogin()
                return@observe
            }*/
            childBinding.userProfile = userProfile
        })
    }

    private fun initFreshLogin() {
        startActivity(Intent(this, SplashActivity::class.java))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finishAffinity()
    }

    private fun initListener() {
    }

    private fun initPreview() {
    }

    override fun getLocale(context: Context): String? {
        return SplashActivity.language
    }
}
