package com.nativedevps.arch.main.ui.datastore_example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityDatastoreBinding
import com.nativedevps.support.base_class.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class DataStoreExampleActivity :
    BaseActivity<ActivityDatastoreBinding, DataStoreExampleActivityViewModel>(
        R.layout.activity_datastore,
        DataStoreExampleActivityViewModel::class.java
    ) {

    override fun onInit(savedInstanceState: Bundle?) {

        initData()
        initListener()
        initPreview()
    }

    private fun initData() {
        viewModel.userProfile.asLiveData().observe(this) {
            binding.text =
                " Name: ${it.firstName}\n" +
                        " LastName: ${it.lastName}\n" +
                        " Email: ${it.emailId}\n"
        }
    }

    private fun initListener() {
        binding.updateMaterialButton.setOnClickListener {
            val name = UUID.randomUUID().toString()
            val lastName = UUID.randomUUID().toString()
            val email = UUID.randomUUID().toString() + "@gmail.com"
            viewModel.update(name, lastName, email)
        }
        binding.clearMaterialButton.setOnClickListener {
            viewModel.clearUserPreference()
        }
    }

    private fun initPreview() {
    }

    companion object {
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, DataStoreExampleActivity::class.java)
        }
    }
}
