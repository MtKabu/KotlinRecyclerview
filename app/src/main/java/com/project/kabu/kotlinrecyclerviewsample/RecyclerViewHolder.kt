package com.project.kabu.kotlinrecyclerviewsample

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    // リストアイテムのレイアウト設定
    val itemTextView : TextView = view.itemTextView

}