package com.nativedevps.arch.main.ui.example_fragment

import android.os.Bundle
import com.nativedevps.arch.databinding.FragmentExampleBinding
import com.nativedevps.support.base_class.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExampleFragment :
    BaseFragment<FragmentExampleBinding, ExampleFragmentViewModel>(
        FragmentExampleBinding::inflate, ExampleFragmentViewModel::class.java
    ) {

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)
        viewModel.onCreate()

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

    companion object {
        const val INTENT_DATA = "intent_data"

        fun getFragment(
        ): ExampleFragment {
            return ExampleFragment().apply {
                arguments = Bundle().apply {
                }
            }
        }
    }
}