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

class SimilarAdapter(
    var itemsList: MutableList<Book>,
    val context: Context,
    val expandListner: expandInterface,
) : RecyclerView.Adapter<SimilarAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image = itemview.findViewById<ImageView>(R.id.similarImg)
        val title = itemview.findViewById<TextView>(R.id.similarTitle)



        init {


            itemview.setOnClickListener {
                val position = adapterPosition
                val item = itemsList[position]
                val category = itemsList[position].category
                expandListner.viewItem(item, category!!)
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_similar, parent, false)
        return ViewHolder(v)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemsList[position]
        holder.title.text = item.title
        val url = item.photo
        Glide.with(context).load(url).placeholder(R.drawable.bookplaceholder).into(holder.image)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }



    interface expandInterface {
        fun viewItem(item: Book, category: String)


    }


}