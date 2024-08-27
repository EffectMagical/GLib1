package com.snow.glib.ui.sorting

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.snow.glib.R

data class GenresListItem(
    val genre: String
)

class GenresListAdapter(
    private var list: List<GenresListItem>,
    val onElementClickListener: (genre:String)->Unit
): RecyclerView.Adapter<GenresListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.genres_list_item, parent, false)
        return GenresListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: GenresListViewHolder, position: Int) {
        if (list[position].genre.length > 6) holder.genre.setTextSize(TypedValue.COMPLEX_UNIT_SP, when(list[position].genre.length){
            7 -> 15F
            8 -> 15F
            9 -> 14F
            10 -> 13F
            11 -> 12F
            else -> 12F
        })
        holder.genre.text = list[position].genre
        holder.itemView.setOnClickListener(){
            onElementClickListener(list[position].genre)
        }
    }

}

class GenresListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val genre: TextView = itemView.findViewById(R.id.genre_text)
}