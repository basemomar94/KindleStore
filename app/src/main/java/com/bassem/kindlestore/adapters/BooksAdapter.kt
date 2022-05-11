package com.bassem.kindlestore.adapters

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bassem.kindlestore.R
import com.bassem.kindlestore.entities.BookClass
import com.bumptech.glide.Glide

class BooksAdapter(
    var itemsList: MutableList<BookClass>,
    val context: Context,
    val expandListener: expandInterface,
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val image = itemview.findViewById<ImageView>(R.id.itemImg)
        val title = itemview.findViewById<TextView>(R.id.itemTitle)
        val favorite = itemview.findViewById<ImageView>(R.id.favoriteItemView)
        val favCard = itemview.findViewById<CardView>(R.id.favCart)


        init {

            itemview.setOnClickListener {
                val position = adapterPosition
                val item = itemsList[position].id
                val category = itemsList[position].category
                expandListener.viewItem(item!!, category!!, position)
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
        val favorite = getDrawable(context, R.drawable.favorange)
        val unfavorite = getDrawable(context, R.drawable.ic_baseline_favorite_border_24)
        val green = R.color.greenPrimary


        if (item.favorite) {
            holder.favCard.setCardBackgroundColor(context.getColorStateList(green))
            holder.favorite.setImageDrawable(favorite)
        } else {
            holder.favorite.setImageDrawable(unfavorite)
            holder.favCard.setCardBackgroundColor(Color.WHITE)


        }


        val url = item.photo
        Glide.with(context).load(url).into(holder.image)

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }



    interface expandInterface {
       // fun makeFavorite(id: String, position: Int, fav: Boolean, item: BookClass)
        fun viewItem(item: String, category: String, position: Int)
     //   fun addCart(id: String, position: Int, item: BookClass)


    }


}