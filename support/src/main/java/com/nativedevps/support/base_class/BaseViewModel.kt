package com.nativedevps.support.base_class

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast

abstract class BaseViewModel constructor(application: Application) :
    AndroidViewModel(application) {
    protected val context: Context get() = getApplication<Application>()
    val liveDataProgressBar = MutableLiveData<Triple<Boolean, Int, String>>()

    abstract fun onCreate()

    open fun showProgressDialog(message: String = "loading..", progress: Int = -1) {
        Log.e("JK", "showProgressDialog")
        context.runOnUiThread {
            liveDataProgressBar.value = Triple(true, progress, message)
        }
    }

    open fun hideProgressDialog() {
        Log.e("JK", "hideProgressDialog")
        context.runOnUiThread {
            liveDataProgressBar.value = Triple(false, -1, "")
        }
    }

    open fun toast(string: String) {
        context.toast(string)
    }

    fun runOnNewThread(callback: suspend CoroutineScope.() -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            callback()
        }
    }

    fun runOnUiThread(callback: () -> Unit) {
        context.runOnUiThread {
            callback()
        }
    }
}