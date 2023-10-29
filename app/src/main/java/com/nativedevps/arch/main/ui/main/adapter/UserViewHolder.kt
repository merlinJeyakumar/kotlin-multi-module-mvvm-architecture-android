package com.nativedevps.arch.main.ui.main.adapter

import com.domain.model.user_list.UsersListResponseModel
import com.nativedevps.arch.databinding.ItemUserBinding
import com.nativedevps.support.base_class.BaseViewHolder

class UserViewHolder(
    private val binding: ItemUserBinding,
    selectionList: MutableList<Int>,
) : BaseViewHolder<UsersListResponseModel.Data, Int>(selectionList, binding.root) {


    override fun bind(position: Int, item: UsersListResponseModel.Data) = with(binding) {
        //todo:
    }
}