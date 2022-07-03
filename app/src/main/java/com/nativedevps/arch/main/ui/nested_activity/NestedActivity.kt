package com.nativedevps.arch.main.ui.nested_activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.data.repositories.local.configuration.PreferencesRepository
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityNestedExampleBinding
import com.nativedevps.arch.main.ui.base.BackgroundActionBarActivity
import com.nativedevps.arch.main.ui.example_list.ExampleListActivity
import com.nativedevps.arch.main.ui.main.MainActivity
import com.nativedevps.support.base_class.BaseActivity
import com.nativedevps.support.coroutines.TimerJob.runAfter
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class NestedActivity : BackgroundActionBarActivity<ActivityNestedExampleBinding, NestedActivityViewModel>(
    R.layout.activity_nested_example,
    NestedActivityViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        initData()
        initListener()
        initPreview()
    }

    private fun initData() {
    }

    private fun initListener() {
    }

    private fun initPreview() {

    }

    override fun getLocale(context: Context): String? {
        return PreferencesRepository(context).getLanguage().also {
            language = it
        }
    }

    companion object {
        var language: String? = null

        fun getIntent(activity: Activity): Intent {
            return Intent(activity, NestedActivity::class.java)
        }
    }
}
