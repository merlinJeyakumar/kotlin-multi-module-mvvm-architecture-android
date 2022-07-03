package com.nativedevps.arch.main.ui.sql_room_example

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.domain.entity.flickr.SampleEntity
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityRoomSqlBinding
import com.nativedevps.support.base_class.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RoomSqlExampleActivity :
    BaseActivity<ActivityRoomSqlBinding, RoomSqlExampleActivityViewModel>(
        R.layout.activity_room_sql,
        RoomSqlExampleActivityViewModel::class.java
    ) {

    override fun onInit(savedInstanceState: Bundle?) {

        initData()
        initListener()
        initPreview()
    }

    var list: List<SampleEntity>? = null
    private fun initData() {
        viewModel.flow.observe(this) {
            list = it
            val arrayAdapter: ArrayAdapter<String> =
                ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, it.map {
                    it.title
                })
            binding.listView.adapter = arrayAdapter
        }
    }

    private fun initListener() {
        binding.addMaterialButton.setOnClickListener {
            viewModel.addItem()
        }
        binding.dropMaterialButton.setOnClickListener {
            viewModel.dropItems()
        }
        binding.listView.setOnItemClickListener { adapterView, view, i, l ->
            list?.get(i)?.let {
                viewModel.removeItem(it.id)
            }
        }
    }

    private fun initPreview() {
    }

    companion object {
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, RoomSqlExampleActivity::class.java)
        }
    }
}
