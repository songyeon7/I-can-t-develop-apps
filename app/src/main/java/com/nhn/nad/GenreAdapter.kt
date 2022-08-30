package com.nhn.nad


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Genre(
    val name:String,
    val songs: MutableList<Song>
)

class GenreAdapter(private val items:List<Genre>) :

    RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    class GenreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            fun from(parent: ViewGroup) : GenreViewHolder {
                return GenreViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.genre_item,parent,false))

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder.from(parent)
    }
    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.genre_title).text = items[position].name
        holder.view.findViewById<RecyclerView>(R.id.songRecycler).adapter =

            SongAdapter(items[position].songs)
    }
    override fun getItemCount(): Int = items.size


}