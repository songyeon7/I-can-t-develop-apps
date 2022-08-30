package com.nhn.nad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Song(
    val name:String,
    val poster:Int,
    val desc:String
)

class SongAdapter(private val items:List<Song>) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.view.findViewById<ImageView>(R.id.imageView).setImageResource(items[position].poster)
        holder.view.findViewById<TextView>(R.id.txtTitle).text = items[position].name
    }
    override fun getItemCount(): Int = items.size
    class SongViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun from(parent: ViewGroup) : SongViewHolder {
                return SongViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.song_item, parent, false))

            }
        }
    }
}