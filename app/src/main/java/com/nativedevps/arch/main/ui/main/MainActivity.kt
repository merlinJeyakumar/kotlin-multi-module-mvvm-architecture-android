package com.nativedevps.arch.main.ui.main

import android.os.Bundle
import com.domain.model.user_list.UsersListResponseModel
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityMainBinding
import com.nativedevps.arch.main.ui.main.adapter.UserAdapter
import com.nativedevps.support.base_class.AbstractRecyclerAdapter
import com.nativedevps.support.base_class.BaseActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    R.layout.activity_main,
    MainViewModel::class.java
) {

    override fun onInit(savedInstanceState: Bundle?) {
        TODO("Not yet implemented")
    }

    private fun initView() = with(binding) {
        //noop
    }

    /*
    * Collect information from Rest API and Cache on the DataStorePreference
    * persistance and update with a UI
    */
    private fun initData() = with(viewModel) {
        //todo:
    }


    /*
    *Initial rendering have no-operations
    */
    private fun initPreview() = with(binding) {
        //noop
    }


    private val itemListener = object :
        AbstractRecyclerAdapter.ItemListener<UsersListResponseModel.Data>() {
        override fun optionSelected(position: Int, item: UsersListResponseModel.Data, option: Int) {
            //todo
        }

        override fun itemSelected(position: Int, item: UsersListResponseModel.Data) {
            //todo
        }
    }

    /*
    * Reusing list from adapter to avoid persistance and network
    */
    private val currencyModelList get() = userAdapter.getList()

    private val userAdapter: UserAdapter by lazy {
        return@lazy UserAdapter().also {
            it.setListener(itemListener)
        }
    }
}