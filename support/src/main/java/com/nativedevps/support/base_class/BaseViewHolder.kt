package com.nativedevps.support.base_class

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.toast

abstract class BaseViewHolder<M, SELECTION_TYPE>(
    private val selectedList: List<SELECTION_TYPE>,
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    val context: Context get() = itemView.context

    fun toast(resId: Int) {
        itemView.context.toast(resId)
    }

    fun toast(text: String) {
        itemView.context.toast(text)
    }

    abstract fun bind(position: Int, item: M)

    open fun bind(
        position: Int,
        item: M,
        payload: List<Any>
    ) {
    }

    open fun setOptions(
        position: Int,
        item: M,
        option: Map<String, Any> = mutableMapOf()
    ) {
    }

    fun getSelectionList(): List<SELECTION_TYPE> {
        return selectedList
    }

    fun isSelected(text: SELECTION_TYPE): Boolean {
        return getSelectionList().contains(text)
    }

    fun isSelectionMode(): Boolean {
        return getSelectionList().isNotEmpty()
    }
}