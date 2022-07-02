package com.nativedevps.arch.main.ui.main

import android.content.Context
import android.os.Bundle
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityMainsBinding
import com.nativedevps.arch.main.ui.example_list.ExampleListActivity
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
        //todo: initialize your get/retrieve/livedata here
    }

    private fun initListener() {
        //todo: keep your view and event listeners
        childBinding.listAdapterMaterialButton.setOnClickListener {
            startActivity(ExampleListActivity.getIntent(this@MainActivity))
        }
    }

    private fun initPreview() {
        //todo: draw or fill your ui
    }

    override fun getLocale(context: Context): String? {
        //todo: return your language selection (sync thread)
        return null
    }
}
