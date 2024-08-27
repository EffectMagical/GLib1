package com.snow.glib.ui.books_info

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.snow.glib.R

data class BooksInfoItem(
    val bookCover: String,
    val name: String,
    val author: String,
    val year: String,
    val genres: String,
    val description: String
)

class BooksInfoListAdapter(
    private var list: List<BooksInfoItem>
): RecyclerView.Adapter<BooksInfoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksInfoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.items_of_fragment_books_info, parent, false)
        return BooksInfoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BooksInfoViewHolder, position: Int) {
        holder.bookCover.load(list[position].bookCover)
        holder.name.text = list[position].name
        holder.author.text = list[position].author
        holder.year.text = list[position].year
        holder.genres.text = list[position].genres
        holder.description.text = list[position].description

    }
}

class BooksInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val bookCover: ImageView = itemView.findViewById(R.id.book_cover_info)
    val name: TextView = itemView.findViewById(R.id.edit_text_name)
    val author: TextView = itemView.findViewById(R.id.edit_text_author)
    val year: TextView = itemView.findViewById(R.id.edit_text_year)
    val genres: TextView = itemView.findViewById(R.id.edit_text_genres)
    val description: TextView = itemView.findViewById(R.id.edit_text_description)
}