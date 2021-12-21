package com.nativedevps.arch.main.ui.example_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nativedevps.domain.model.example_list.ExampleApiModelItem
import com.nativedevps.arch.databinding.ItemExampleListBinding
import com.nativedevps.support.base_class.BaseAdapter2
import com.nativedevps.support.base_class.BaseViewHolder

class ExampleAdapter(private val itemListener: ItemListener? = null) :
    BaseAdapter2<ExampleApiModelItem, Int>() {
    override fun getList(): List<ExampleApiModelItem> {
        return currentList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ExampleApiModelItem, Int> {
        return ExampleViewHolder(
            ItemExampleListBinding.inflate(LayoutInflater.from(parent.context)),
            getSelections()
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ExampleApiModelItem, Int>, position: Int) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            itemListener?.onItemSelected(position, currentList[position])
        }
    }

    interface ItemListener {
        fun onItemSelection(position: Int, item: ExampleApiModelItem) {}
        fun onItemSelected(position: Int, item: ExampleApiModelItem) {}
        fun onOptionSelected(
            view: View? = null,
            position: Int,
            item: ExampleApiModelItem,
        ) {
        }
    }

    override fun isSameItem(oldItem: ExampleApiModelItem, newItem: ExampleApiModelItem): Boolean {
        return oldItem.name == newItem.name
    }

    override fun isSameContent(
        oldItem: ExampleApiModelItem,
        newItem: ExampleApiModelItem,
    ): Boolean {
        return oldItem.image == newItem.image
    }
}