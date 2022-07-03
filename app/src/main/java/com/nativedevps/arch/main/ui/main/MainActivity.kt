package com.nativedevps.arch.main.ui.main

import android.content.Context
import android.os.Bundle
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityMainBinding
import com.nativedevps.arch.main.ui.base.BackgroundActionBarActivity
import com.nativedevps.arch.main.ui.datastore_example.DataStoreExampleActivity
import com.nativedevps.arch.main.ui.example_list.ExampleListActivity
import com.nativedevps.arch.main.ui.nested_activity.NestedActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BackgroundActionBarActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
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
        rootBinding.listAdapterMaterialButton.setOnClickListener {
            startActivity(ExampleListActivity.getIntent(this@MainActivity))
        }
        rootBinding.exampleNestedMaterialButton.setOnClickListener {
            startActivity(NestedActivity.getIntent(this@MainActivity))
        }
        rootBinding.datastoreMaterialButton.setOnClickListener {
            startActivity(DataStoreExampleActivity.getIntent(this@MainActivity))
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
