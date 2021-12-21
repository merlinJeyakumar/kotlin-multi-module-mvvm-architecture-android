package com.nativedevps.support.base_class.dialog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.viewbinding.ViewBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nativedevps.support.inline.orElse
import org.jetbrains.anko.layoutInflater

abstract class BaseMaterialDialog<B : ViewBinding>(
    context: Context,
    private val bindingFactory: (LayoutInflater) -> B,
) : MaterialAlertDialogBuilder(context) {

    protected var alertDialog: AlertDialog? = null
    private var _binding: ViewBinding = bindingFactory.invoke(context.layoutInflater)
    protected val binding: B by lazy { _binding as B }

    override fun create(): AlertDialog {
        setView(binding.root)
        return super.create()
    }

    override fun setView(view: View?): MaterialAlertDialogBuilder {
        super.setView(binding.root).let {
            return it.also {
                onCreate(binding)
            }
        }
    }

    override fun show(): AlertDialog {
        return super.show().also {
            this.alertDialog = it
            onShow(alertDialog)
        }
    }

    fun dismiss() {
        this.alertDialog?.dismiss()
    }

    open fun isShowing(): Boolean {
        return alertDialog?.isShowing.orElse { false }
    }

    abstract fun onCreate(binding: B)

    protected open fun onShow(alertDialog: AlertDialog?) {}
}