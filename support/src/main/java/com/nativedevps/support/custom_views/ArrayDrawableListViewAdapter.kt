package com.nativedevps.support.custom_views

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.nativedevps.support.utility.view.ViewUtils.setLeftDrawable
import nativedevps.support.R
import nativedevps.support.databinding.ItemSimpleListViewBinding

class ArrayDrawableListViewAdapter(
    var activity: Activity,
    var items: List<Pair<Int, String>>,
) : ArrayAdapter<Pair<Int, String>>(activity, R.layout.item_simple_list_view, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = ItemSimpleListViewBinding.inflate(activity.layoutInflater, parent, false)
        binding.text1.setLeftDrawable(items[position].first)
        binding.text1.text = items[position].second
        return binding.root
    }
}