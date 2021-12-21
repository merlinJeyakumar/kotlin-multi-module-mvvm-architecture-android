package com.nativedevps.support.base_class

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import nativedevps.support.R
import nativedevps.support.databinding.ActivityActionbarBinding

abstract class ActionBarActivity<VB : ViewDataBinding, VM : BaseViewModel>(
    private val layout: Int,
    viewModelClass: Class<VM>,
) : BaseActivity<ActivityActionbarBinding, VM>(R.layout.activity_actionbar, viewModelClass) {

    protected lateinit var childBinding: VB

    /*super call required*/
    override fun onInit(savedInstanceState: Bundle?) {
        viewBind()
        initActionBar()
    }

    private fun viewBind() {
        childBinding =
            DataBindingUtil.inflate(layoutInflater, layout, binding.rootFrameLayout, false)
        binding.rootFrameLayout.addView(childBinding.root)
    }

    private fun initActionBar() {
        setSupportActionBar(binding.toolbar)
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    fun setNavigateUpEnabled(boolean: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(boolean)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}