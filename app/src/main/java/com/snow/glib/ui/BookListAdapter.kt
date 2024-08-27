package com.snow.glib.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.snow.glib.R

data class BookListDataItem(
    val id: String,
    val img: String,
    val name: String,
    val author: String,
    val genres: String,
//    val isLiked: Boolean
)

class BookListAdapter(
    private var list: List<BookListDataItem>,
    val onElementClickListener: (id:String)->Unit
): RecyclerView.Adapter<BookListItemViewHolder>() {

    fun filterList(filterList: List<BookListDataItem>){
        list = filterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_book, parent, false)
        return BookListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BookListItemViewHolder, position: Int) {
//        holder.imgClickForFav.load(list[position].isLiked)
        holder.imgBook.load(list[position].img)
        holder.bookName.text = list[position].name
        holder.bookAuthor.text = list[position].author
        holder.bookGenres.text = list[position].genres
        holder.itemView.setOnClickListener(){
            onElementClickListener(list[position].id)
        }
    }
}

class BookListItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
//    val imgClickForFav: ImageView = itemView.findViewById(R.id.click_for_fav)
    val imgBook: ImageView = itemView.findViewById(R.id.book_cover_item)
    val bookName: TextView = itemView.findViewById(R.id.book_name_item)
    val bookAuthor: TextView = itemView.findViewById(R.id.book_author_item)
    val bookGenres: TextView = itemView.findViewById(R.id.book_genres_item)
}