package com.example.roman.firstandroidapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.repo_row.view.*

/**
 * Created by Roman on 10.02.2018.
 */
class recViewAdapter(val c: Context, val repos: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as Item).bindData(repos[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(c).inflate(R.layout.repo_row, parent, false);
        return Item(v)
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    class Item(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(_list: String) {
            itemView.textView.text = _list
        }
    }
}