package com.project.kabu.kotlinrecyclerviewsample

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class RecyclerAdapter(private val context: Context,
                     private val itemClickListener: ItemClickListener,
                     private val itemList:List<String>) : RecyclerView.Adapter<RecyclerViewHolder>() {

    // リストタップ処理用のインターフェース
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }


    // RecyclerViewのアダプター設定
    private var mRecyclerView : RecyclerView? = null

    // このアダプタの監視を開始するときに呼び出される。
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        // RecyclerViewの設定
        mRecyclerView = recyclerView
    }

    // このアダプタの監視を停止したときに呼び出される
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        // RecyclerViewの破棄
        mRecyclerView = null
    }

    // 指定された位置にデータを表示する
    // 指定された位置の項目を反映するために、ViewHolder#itemViewの内容を更新する必要がある
    //  後で（例えばクリックリスナーで）アイテムの位置が必要な場合は、更新されたアダプタ位置を持つViewHolder＃getAdapterPosition（）を使用してください。
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        // データをビューに設定
        holder.itemTextView.text = itemList.get(position)
    }

    // アダプターが保持するデータ・セット内の項目の合計数を戻します。
    override fun getItemCount(): Int {
        // アダプタが保持するデータの合計数
        return itemList.size
    }

    // リストのアイテムのレイアウトを変える・切り替えるときに使える, returnするViewHolderを変更すればOK。
    // RecyclerViewがアイテムを表すために, 指定されたタイプの新しいViewHolderを必要とするときに呼び出されます。
    // この新しいViewHolderは、指定された型の項目を表す新しいViewで構築する必要があります。 (新しいビューを手動で作成することも、XMLレイアウトファイルから拡張することもできます。)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        // レイアウトの設定
        val layoutInflater = LayoutInflater.from(context)
        val mView = layoutInflater.inflate(R.layout.list_item, parent, false)

        // リスト項目にオンクリックリスナー設定
        mView.setOnClickListener {
            view -> mRecyclerView?.let {
                itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
            }
        }

        return RecyclerViewHolder(mView)
    }

}