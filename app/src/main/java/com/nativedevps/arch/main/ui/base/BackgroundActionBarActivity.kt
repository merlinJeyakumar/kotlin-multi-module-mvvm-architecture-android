package com.nativedevps.arch.main.ui.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityBackgroundBinding
import com.nativedevps.support.base_class.ActionBarActivity
import com.nativedevps.support.base_class.BaseViewModel


abstract class BackgroundActionBarActivity<VB : ViewDataBinding, VM : BaseViewModel>(
    private val layout: Int,
    viewModelClass: Class<VM>,
) : ActionBarActivity<ActivityBackgroundBinding, VM>(
    R.layout.activity_background,
    viewModelClass
) {

    protected lateinit var rootBinding: VB

    /*inheriting child class require a super call*/
    override fun onInit(savedInstanceState: Bundle?) {

        viewBind()
        initActionBar()
        initListener()
    }

    private fun viewBind() {
        rootBinding =
            DataBindingUtil.inflate(layoutInflater, layout, binding.rootFrameLayout, false)
        binding.rootFrameLayout.addView(rootBinding.root)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    private fun initListener() {
        //todo:
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}