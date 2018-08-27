package com.project.kabu.kotlinrecyclerviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity(), RecyclerAdapter.ItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 表示データ
        val cityArray = resources.getStringArray(R.array.city).toMutableList()

        // 表示データを設定
        mainRecyclerView.adapter = RecyclerAdapter(this, this, cityArray)
        mainRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // Recyclerviewの拡張、"SwipeRefreshLayout"
        // 上に引っ張ると、リストが更新されるレイアウトの設定方法
        // SwipeRefreshLayoutにリスナー設定(onRefreshに通知)
        swiperefresh.setOnRefreshListener(this)
    }

    override fun onItemClick(view: View, position: Int) {
        Snackbar.make(view, view.itemTextView.text, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

    // SwipeRefreshLayoutのリスナー
    // 一覧の更新とかを記載
    override fun onRefresh() {
        // 注意点
        // isRefreshingをfalseにしないと、ずっと回り続ける
        // 更新などが終了したら、falseを設定するように
        swiperefresh.isRefreshing = false
    }
}
