package com.nativedevps.arch.main.ui.example_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.domain.model.example_list.ResponseCharacterList
import com.nativedevps.arch.databinding.ItemExampleListBinding
import com.nativedevps.support.base_class.BaseAdapter2
import com.nativedevps.support.base_class.BaseViewHolder

class ExampleAdapter(private val itemListener: ItemListener? = null) :
    BaseAdapter2<ResponseCharacterList.ExampleApiModelItem, Int>() {
    override fun getList(): List<ResponseCharacterList.ExampleApiModelItem> {
        return currentList
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<ResponseCharacterList.ExampleApiModelItem, Int> {
        return ExampleViewHolder(
            ItemExampleListBinding.inflate(LayoutInflater.from(parent.context)),
            getSelections()
        )
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<ResponseCharacterList.ExampleApiModelItem, Int>,
        position: Int
    ) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            itemListener?.onItemSelected(position, currentList[position])
        }
    }

    interface ItemListener {
        fun onItemSelection(position: Int, item: ResponseCharacterList.ExampleApiModelItem) {}
        fun onItemSelected(position: Int, item: ResponseCharacterList.ExampleApiModelItem) {}
        fun onOptionSelected(
            view: View? = null,
            position: Int,
            item: ResponseCharacterList.ExampleApiModelItem,
        ) {
        }
    }

    override fun isSameItem(
        oldItem: ResponseCharacterList.ExampleApiModelItem,
        newItem: ResponseCharacterList.ExampleApiModelItem
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun isSameContent(
        oldItem: ResponseCharacterList.ExampleApiModelItem,
        newItem: ResponseCharacterList.ExampleApiModelItem,
    ): Boolean {
        return oldItem.image == newItem.image
    }
}