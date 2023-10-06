package com.example.snapnews

import android.net.wifi.p2p.WifiP2pManager.NetworkInfoListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class SnapNewsAdaptor(private val listener:NewsItemClicked): RecyclerView.Adapter<SnapNewsViewHolder>() {

   private val item:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SnapNewsViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
     val viewHolder=SnapNewsViewHolder(view)
        view.setOnClickListener {
      listener.onItemClicked(item[viewHolder.adapterPosition])
     }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return item.size
    }

    override fun onBindViewHolder(holder: SnapNewsViewHolder, position: Int) {

        val currenItem=item[position]
        holder.Titleview.text=currenItem.title
        holder.author.text=currenItem.author
        Glide.with(holder.itemView.context).load(currenItem.imageUrl).into(holder.image)

    }

    fun updateNews(updatedNews: ArrayList<News>){
        item.clear()
        item.addAll(updatedNews)

        notifyDataSetChanged()
    }

}

class SnapNewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    var Titleview = itemView.findViewById<TextView>(R.id.title)
    val image= itemView.findViewById<ImageView>(R.id.image)
    val author= itemView.findViewById<TextView>(R.id.author)




}

interface NewsItemClicked{
  fun onItemClicked(item:News)
}