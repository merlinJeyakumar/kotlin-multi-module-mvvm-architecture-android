package com.nativedevps.arch.main.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityMainsBinding
import com.nativedevps.arch.main.ui.splash.SplashActivity
import com.nativedevps.support.base_class.ActionBarActivity
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

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

        viewModel.retrieveUserProfile { success, model, error ->
            if (success) {
                toast("Profile updated!")
            } else {
                toast("failed: $error")
            }
        }
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
