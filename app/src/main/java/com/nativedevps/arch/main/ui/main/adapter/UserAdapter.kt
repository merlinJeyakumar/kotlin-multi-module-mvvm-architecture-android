package com.nativedevps.arch.main.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.domain.model.user_list.UsersListResponseModel
import com.nativedevps.arch.databinding.ItemUserBinding
import com.nativedevps.support.base_class.AbstractRecyclerAdapter
import com.nativedevps.support.base_class.BaseViewHolder

open class UserAdapter : AbstractRecyclerAdapter<UsersListResponseModel.Data, Int>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): BaseViewHolder<UsersListResponseModel.Data, Int> {
        return UserViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), getSelections()
        )
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<UsersListResponseModel.Data, Int>,
        position: Int
    ) {
        holder.bind(position, currentList[position])
        holder.itemView.setOnClickListener {
            itemListener?.itemSelected(position, currentList[position])
        }
    }

    override fun getList(): List<UsersListResponseModel.Data> {
        return currentList
    }

    override fun isSameItem(
        oldItem: UsersListResponseModel.Data, newItem: UsersListResponseModel.Data
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun isSameContent(
        oldItem: UsersListResponseModel.Data,
        newItem: UsersListResponseModel.Data,
    ): Boolean {
        return oldItem == newItem
    }

    interface ItemListener{
        fun onItemSelected(position: Int, item: UsersListResponseModel.Data) {}
    }
}