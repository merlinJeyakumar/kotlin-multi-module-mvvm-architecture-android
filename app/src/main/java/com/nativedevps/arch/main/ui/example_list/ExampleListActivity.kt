package com.nativedevps.arch.main.ui.example_list

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.domain.model.example_list.ResponseCharacterList
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
) {

    private var alertDialog: AlertDialog? = null
    private lateinit var exampleAdapter: ExampleAdapter

    override fun onInit(savedInstanceState: Bundle?) {
        super.onInit(savedInstanceState)

        initData()
        initObserver()
        initListener()
        initPreview()
    }

    private fun initData() {
        viewModel.retrieveExampleList { success, error ->
            if (! success) {
                toast("failed $error")
            }
        }
    }

    private fun initObserver() {
        viewModel.characterListLiveData.observe(this) {
            if (! ::exampleAdapter.isInitialized) {
                exampleAdapter = ExampleAdapter(itemListener)
                childBinding.bankRecyclerView.adapter = exampleAdapter
            }
            exampleAdapter.submitList(it)
        }
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

    override fun onDestroy() {
        super.onDestroy()
        alertDialog?.dismiss()
    }

    override fun getLocale(context: Context): String? {
        return SplashActivity.language
    }

    private val itemListener = object : ExampleAdapter.ItemListener {
        override fun onItemSelected(
            position: Int,
            item: ResponseCharacterList.ExampleApiModelItem
        ) {
            if (alertDialog?.isShowing == true) { //todo: ensure already alert dialog open
                return
            }
            alertDialog =
                listDialog(title = null, stringList = listOf(
                    Pair(
                        R.drawable.ic_baseline_delete_24,
                        "delete"
                    )
                ), callback = { success, posText ->
                    alertDialog = null
                    if (success) {
                        alertDialog = confirmationDialog(callback = { success ->
                            //todo: optional usage
                        })
                    }
                })
        }
    }

    companion object {
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, ExampleListActivity::class.java)
        }
    }
}
