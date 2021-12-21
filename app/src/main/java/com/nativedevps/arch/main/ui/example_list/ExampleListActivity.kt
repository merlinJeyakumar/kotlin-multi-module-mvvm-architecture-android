package com.nativedevps.arch.main.ui.example_list

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.asLiveData
import com.nativedevps.domain.model.example_list.ExampleApiModelItem
import com.nativedevps.arch.R
import com.nativedevps.arch.databinding.ActivityExampleListBinding
import com.nativedevps.arch.main.ui.example_list.adapter.ExampleAdapter
import com.nativedevps.arch.main.ui.splash.SplashActivity
import com.nativedevps.support.base_class.ActionBarActivity
import com.nativedevps.support.utility.view.DialogBox.confirmationDialog
import com.nativedevps.support.utility.view.DialogBox.listDialog
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.toast

@AndroidEntryPoint
class ExampleListActivity : ActionBarActivity<ActivityExampleListBinding, ExampleListViewModel>(
    R.layout.activity_example_list,
    ExampleListViewModel::class.java
), ExampleAdapter.ItemListener {

    private var alertDialog: AlertDialog? = null
    private lateinit var exampleAdapter: ExampleAdapter

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initData()
        initFetching()
        initListener()
        initPreview()
    }

    private fun initData() {
        viewModel.userProfile.asLiveData().observe(this, { userProfile ->
            //todo: attach user data to ui
        })
        viewModel.retrieveExampleList { success, model, error ->
            if (success) {
                if (!::exampleAdapter.isInitialized) {
                    exampleAdapter = ExampleAdapter(this)
                    childBinding.bankRecyclerView.adapter = exampleAdapter
                }
                exampleAdapter.submitList(model)
            } else {
                toast("failed $error")
            }
        }
    }

    private fun initFetching() {
    }

    private fun initListener() {
    }

    private fun initPreview() {
        setTitle("Example List")
        setNavigateUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onItemSelected(position: Int, item: ExampleApiModelItem) {
        if (alertDialog != null && alertDialog?.isShowing!!) { //todo: ensure already alert dialog open
            return
        }
        alertDialog =
            listDialog(title = null, stringList = listOf(
                Pair(R.drawable.ic_baseline_delete_24,
                    "delete")
            ), callback = { success, posText ->
                alertDialog = null
                if (success) {
                    alertDialog = confirmationDialog(callback = { success ->
                        //todo: optional usage
                    })
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        alertDialog?.dismiss()
    }

    override fun getLocale(context: Context): String? {
        return SplashActivity.language
    }
}
