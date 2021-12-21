package com.nativedevps.support.utility.view

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.SystemClock
import android.view.*
import android.widget.FrameLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi


object ViewUtils {
    private const val TAG = "ViewUtils"
    fun getViewFromLayout(mActivity: Activity, mContainerID: Int, mLayoutID: Int): View {
        val activityContainer = mActivity.findViewById<FrameLayout>(mContainerID)
        activityContainer.removeAllViews()
        val vi = mActivity.layoutInflater.inflate(mLayoutID, null)
        activityContainer.addView(vi)
        return activityContainer
    }

    fun getViewFromLayout(
        mActivity: Activity,
        activityContainer: FrameLayout,
        mLayoutID: Int,
    ): View {
        activityContainer.removeAllViews()
        val vi = mActivity.layoutInflater.inflate(mLayoutID, null)
        activityContainer.addView(vi)
        return activityContainer
    }

    fun getViewFromLayout(mActivity: Activity, mLayoutID: Int): View {
        return mActivity.layoutInflater.inflate(mLayoutID, null, false)
    }

    fun getSmoothAnimation(activity: Activity) {
        activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    fun setViewAndChildrenEnabled(view: View, enabled: Boolean) {
        view.isEnabled = enabled
        if (view is ViewGroup) {
            val viewGroup = view
            for (i in 0 until viewGroup.childCount) {
                val child = viewGroup.getChildAt(i)
                setViewAndChildrenEnabled(child, enabled)
            }
        }
    }

    fun getPopup(
        mInstance: Activity?,
        popupWindow: PopupWindow?,
        contentView: View?,
        anchorView: View?,
    ): PopupWindow {
        var popupWindow = popupWindow
        if (popupWindow == null) {
            popupWindow = PopupWindow(mInstance)
        }
        popupWindow.isFocusable = true
        popupWindow.contentView = contentView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 5f
        }
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.isOutsideTouchable = true
        popupWindow.showAsDropDown(anchorView) // where u want show on view click event popupwindow.showAsDropDown(view, x, y);
        return popupWindow
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarColor(activity: Activity, color: Int) {
        val window: Window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.setStatusBarColor(color)
    }

    fun TextView.setLeftDrawable(@DrawableRes drawable: Int) {
        setCompoundDrawablesWithIntrinsicBounds(drawable, 0, 0, 0);
    }

    fun TextView.setTopDrawable(@DrawableRes drawable: Int) {
        setCompoundDrawablesWithIntrinsicBounds(0, drawable, 0, 0);
    }

    fun TextView.setRightDrawable(@DrawableRes drawable: Int) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
    }

    fun TextView.setBottomDrawable(@DrawableRes drawable: Int) {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, drawable);
    }

    fun View.setClickListener(onClickEvent: (view: View) -> Unit) {
        this.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                onClickEvent(v)
            }
            return@setOnTouchListener true
        }
    }

    fun View.click() {
        val downTime: Long = SystemClock.uptimeMillis()
        val eventTime: Long = SystemClock.uptimeMillis() + 100
        val x = 0.0f
        val y = 0.0f
        val metaState = 0
        val motionEvent = MotionEvent.obtain(
            downTime,
            eventTime,
            MotionEvent.ACTION_DOWN,
            x,
            y,
            metaState
        )
        dispatchTouchEvent(motionEvent)
    }

    fun View.gone() {
        this.visibility = View.GONE
    }

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.invisible() {
        this.visibility = View.INVISIBLE
    }
}