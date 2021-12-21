package com.nativedevps.support.utility.view

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nativedevps.support.custom_views.ArrayDrawableListViewAdapter
import nativedevps.support.databinding.DialogListBinding

object DialogBox {
    fun Activity.confirmationDialog(
        title: String = "Alert",
        message: String = "Are you sure?",
        isCancellable: Boolean = true,
        negativeText: String = "Cancel",
        positiveText: String = "Ok",
        callback: (success: Boolean) -> Unit,
    ): AlertDialog {
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
        materialAlertDialogBuilder.setTitle(title)
        materialAlertDialogBuilder.setCancelable(isCancellable)
        message.let {
            materialAlertDialogBuilder.setMessage(it)
        }
        materialAlertDialogBuilder.setNegativeButton(negativeText) { dialog, which ->
            materialAlertDialogBuilder.create().dismiss()
            callback(false)
        }
        materialAlertDialogBuilder.setPositiveButton(positiveText) { dialog, which ->
            materialAlertDialogBuilder.create().dismiss()
            callback(true)
        }
        return materialAlertDialogBuilder.show()
    }

    fun Activity.listDialog(
        title: String? = "Alert",
        isCancellable: Boolean = true,
        stringList: List<Pair<Int, String>>,
        negativeText: String? = "Cancel",
        callback: (success: Boolean, selection: Pair<Int, String>?) -> Unit,
    ): AlertDialog {
        var alertDialog: AlertDialog? = null
        val binding = DialogListBinding.inflate(layoutInflater)
        binding.itemsListView.adapter = ArrayDrawableListViewAdapter(this, stringList)
        binding.itemsListView.setOnItemClickListener { adapterView, view, position, id ->
            alertDialog?.dismiss()
            callback(true, Pair(position, stringList[position].second))
        }
        val materialAlertDialogBuilder = MaterialAlertDialogBuilder(this)
        materialAlertDialogBuilder.setView(binding.root)
        materialAlertDialogBuilder.setTitle(title)
        materialAlertDialogBuilder.setCancelable(isCancellable)

        if (negativeText != null) {
            materialAlertDialogBuilder.setNegativeButton(negativeText) { dialog, which ->
                materialAlertDialogBuilder.create().dismiss()
                callback(false, null)
            }
        }

        return materialAlertDialogBuilder.show().apply {
            alertDialog = this
            alertDialog?.setOnCancelListener {
                callback(false, null)
            }
        }
    }
}