package com.bassem.kindlestore.adapters

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bassem.kindlestore.R
import com.bassem.kindlestore.entities.Book
import com.bumptech.glide.Glide

class BooksAdapter(
    var itemsList: MutableList<Book>,
    val context: Context,
    val expandListener: expandInterface,
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image = itemview.findViewById<ImageView>(R.id.itemImg)
        val title = itemview.findViewById<TextView>(R.id.itemTitle)


        init {

            itemview.setOnClickListener {
                val position = adapterPosition
                val item = itemsList[position]
                val category = itemsList[position].category
                expandListener.viewItem(item, position)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        val itemPostien = position
        holder.title.text = item.title
        val url = item.photo
        Glide.with(context).load(url).placeholder(R.drawable.bookplaceholder).into(holder.image)


    }

    override fun getItemCount(): Int {
        return itemsList.size
    }



    interface expandInterface {
       // fun makeFavorite(id: String, position: Int, fav: Boolean, item: BookClass)
        fun viewItem(book:Book, position: Int)
     //   fun addCart(id: String, position: Int, item: BookClass)


    }


}