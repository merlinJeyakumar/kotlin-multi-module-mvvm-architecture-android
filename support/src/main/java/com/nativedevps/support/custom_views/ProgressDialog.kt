package com.nativedevps.support.custom_views

import android.content.Context
import android.os.Build
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.nativedevps.support.base_class.dialog.BaseMaterialDialog
import nativedevps.support.databinding.DProgressBarBinding


class ProgressDialog(context: Context) :
    BaseMaterialDialog<DProgressBarBinding>(context, DProgressBarBinding::inflate) {

    override fun onCreate(binding: DProgressBarBinding) {
        setMessage("Loading..")
        progressBar.setOnClickListener {
            setProgress(progressBar.progress + 10)
        }
    }

    fun setIndeterminate(boolean: Boolean): ProgressDialog {
        progressBar.isIndeterminate = boolean
        return this
    }

    fun setMessage(string: String): ProgressDialog {
        binding.messageAppCompatTextView.text = string
        return this
    }

    fun setProgress(int: Int): ProgressDialog {
        if (int == -1) {
            return this
        }
        if (progressBar.isIndeterminate) {
            progressBar.isIndeterminate = false
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            progressBar.setProgress(int, true)
        } else {
            progressBar.progress = int
        }
        return this
    }

    fun build(): ProgressDialog {
        if (!isShowing()) {
            show()
        }
        return this
    }

    private val progressBar: CircularProgressIndicator get() = binding.progressCircularProgressIndicator
}