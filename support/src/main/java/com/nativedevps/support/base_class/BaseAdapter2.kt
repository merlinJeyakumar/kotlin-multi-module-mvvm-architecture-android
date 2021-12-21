package com.nativedevps.support.base_class

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.nativedevps.support.custom_views.ListAdapter
import com.nativedevps.support.inline.orElse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

abstract class BaseAdapter2<ITEM_TYPE, SELECTION_TYPE>() :
    ListAdapter<ITEM_TYPE, BaseViewHolder<ITEM_TYPE, SELECTION_TYPE>>() {

    private val TAG: String = "BaseAdapter"
    private val selectionList: MutableList<SELECTION_TYPE> = ArrayList()

    init {
        val diffItemCallback = object : DiffUtil.ItemCallback<ITEM_TYPE>() {
            override fun areItemsTheSame(
                oldItem: ITEM_TYPE,
                newItem: ITEM_TYPE,
            ): Boolean {
                return isSameItem(oldItem, newItem)
            }

            override fun areContentsTheSame(
                oldItem: ITEM_TYPE,
                newItem: ITEM_TYPE,
            ): Boolean {
                return isSameContent(oldItem, newItem)
            }

            override fun getChangePayload(oldItem: ITEM_TYPE, newItem: ITEM_TYPE): Any? {
                return getChangePayload()?.let { it }
                    .orElse { super.getChangePayload(oldItem, newItem) }
            }
        }
        init(diffItemCallback)
    }

    companion object {
        const val PAYLOAD_SELECTION_MODE: String = "is_selection_mode"
    }

    fun setSelected(key: SELECTION_TYPE) {
        if (isSelectable(key)) {
            if (selectionList.contains(key)) {
                Log.e(TAG, "error: item already selected")
                return
            }
            selectionList.add(key)
            notifyItemChanged(
                getIndex(key),
                PAYLOAD_SELECTION_MODE
            )
        }
    }

    fun clearSelection(topic: SELECTION_TYPE) {
        if (!selectionList.contains(topic)) {
            Log.e(TAG, "error: item already cleared")
            return
        }
        selectionList.remove(topic)
        notifyItemChanged(
            getIndex(topic),
            PAYLOAD_SELECTION_MODE
        )
    }

    fun clearSelection(notifyAll: Boolean = false) {
        if (!notifyAll) {
            for (topicId in selectionList) {
                val itemId = getIndex(topicId)
                if (itemId != -1) {
                    notifyItemChanged(itemId, PAYLOAD_SELECTION_MODE)
                } else {
                    Log.e(TAG, "error: item not found $topicId")
                }
            }
        } else {
            notifyDataSetChanged()
        }
        selectionList.clear()
    }

    fun selectAll(notifyAll: Boolean = true) {
        val selectableKeys = mutableListOf<SELECTION_TYPE>()
        for (key in getAllKeys()) {
            if (isSelectable(key)) {
                selectableKeys.add(key)
            }
        }
        selectionList.clear()
        selectionList.addAll(selectableKeys)
        if (notifyAll) {
            notifyDataSetChanged()
        } else {
            for (selectableKey in selectableKeys) {
                val itemId = getIndex(selectableKey)
                if (itemId != -1) {
                    notifyItemChanged(itemId, PAYLOAD_SELECTION_MODE)
                }
            }
        }

    }

    fun isSelected(topic: SELECTION_TYPE): Boolean {
        return selectionList.contains(topic)
    }

    fun isSelectionMode(): Boolean {
        return selectionList.isNotEmpty()
    }

    fun getSelections(): MutableList<SELECTION_TYPE> {
        return selectionList
    }

    abstract fun getList(): List<ITEM_TYPE>

    open fun getIndex(itemKey: SELECTION_TYPE): Int {
        return -1
    }

    open fun getAllKeys(): List<SELECTION_TYPE> {
        return listOf()
    }

    open fun isSelectable(key: SELECTION_TYPE): Boolean {
        return true
    }

    open fun isSameItem(oldItem: ITEM_TYPE, newItem: ITEM_TYPE): Boolean {
        return false
    }

    open fun isSameContent(oldItem: ITEM_TYPE, newItem: ITEM_TYPE): Boolean {
        return true
    }

    open fun getChangePayload(): Any? {
        return null
    }

    suspend fun runOnUiThread(callback: suspend CoroutineScope.() -> Unit) {
        withContext(Dispatchers.Main) {
            callback()
        }
    }
}