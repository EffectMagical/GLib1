package com.snow.glib.ui.sorting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import com.snow.glib.R
import com.snow.glib.databinding.FragmentSortingBinding
import com.snow.glib.ui.BookListAdapter
import com.snow.glib.ui.BookListDataItem

class SortingFragment : Fragment() {

    private lateinit var binding: FragmentSortingBinding
    private var bundle = Bundle()
    private var sortingBookList = mutableListOf<BookListDataItem>()
    private var genresList = mutableListOf<GenresListItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSortingBinding.inflate(inflater, container, false)
        // заполнение списка genresList
        genresList.add(
            GenresListItem(
            "Бизнес- процессы"
        )
        )
        genresList.add(
            GenresListItem(
            "Бизнес- стратегии"
        )
        )
        genresList.add(
            GenresListItem(
            "Зарубежная деловая литература"
        )
        )
        genresList.add(
            GenresListItem(
            "Менеджмент и кадры"
        )
        )
        genresList.add(
            GenresListItem(
                "Привлечение клиентов"
            )
        )
        genresList.add(
            GenresListItem(
                "Техника продаж"
            )
        )
        genresList.add(
            GenresListItem(
                "Управление продажами"
            )
        )

        // check bundle, if were transition === is not working, but why?
        if (arguments?.containsKey("SortingState") == true) {
            // draw the needed "window"
            if (arguments?.getString("SortingState") == "Authors") authorsWindow()
            else namesWindow(true)
        }
        // if no transition than start with genres block list
        else{
            // "open" genres list
            genresWindow()
            // click event "button" one of genres
            clickedGenres()
        }
        // back button to block genres "gone" just like a name
        binding.backBtnGenre.setOnClickListener{
            binding.backBtnGenre.visibility = View.GONE
            binding.genreNameText.visibility = View.GONE
            clickedGenres()
            genresWindow()
        }

        // "button" click event
        binding.cbtnGenres.setOnClickListener {
            genresWindow()
            binding.sortingList.adapter = GenresListAdapter(genresList){
                namesWindow(false)
            }
        }
        binding.cbtnAuthors.setOnClickListener {
            sortingBookList.clear()
            authorsWindow()
        }
        binding.cbtnNames.setOnClickListener {
            sortingBookList.clear()
            namesWindow(true)
        }

        return binding.root
    }

    // чтение data
    private fun getDataFromServer(){
        val query = ParseQuery.getQuery<ParseObject>("Book")
        query.findInBackground(object : FindCallback<ParseObject> {
            override fun done(objects: List<ParseObject>?, e: ParseException?) {
                if (e == null) {
                    if (objects != null) {
                        for (i in 0..<objects.size) {
                            val elemId = objects[i].objectId
                            val cover = objects[i].getString("CoverTop").toString()
                            var name = objects[i].getString("Name").toString()
                            var author = objects[i].getString("Author").toString()
                            if ("," in author) author = author.split(",").toList()[0] + "..."

                            var genres = objects[i].getString("Genres").toString()
                            if ("," in genres) genres = genres.split(",").toList()[0] + "..."

                            sortingBookList.add(BookListDataItem(elemId, cover, name, author, genres))
                        }
                        binding.sortingList.adapter = BookListAdapter(sortingBookList) {
                            bundle.putString("bookId", it)
                            bundle.putString("FromFragment", "Sorting")
                            Navigation.findNavController(binding.root).navigate(R.id.booksInfoFragment, bundle)
                        }
                    }
                }
                else {
                    // handling error if we get any error.
                }
            }
        })
    }

    private fun getDataFromServerNames(){
        val query = ParseQuery.getQuery<ParseObject>("Book")
        query.findInBackground(object : FindCallback<ParseObject> {
            override fun done(objects: List<ParseObject>?, e: ParseException?) {
                if (e == null) {
                    if (objects != null) {
                        for (i in 0..<objects.size) {
                            val elemId = objects[i].objectId
                            val cover = objects[i].getString("CoverTop").toString()
                            var name = objects[i].getString("Name").toString()
                            var author = objects[i].getString("Author").toString()
                            if ("," in author) author = author.split(",").toList()[0] + "..."

                            var genres = objects[i].getString("Genres").toString()
                            if ("," in genres) genres = genres.split(",").toList()[0] + "..."

                            sortingBookList.add(BookListDataItem(elemId, cover, name, author, genres))
                        }
                        binding.sortingList.adapter = BookListAdapter(sortingBookList) {
                            bundle.putString("bookId", it)
                            bundle.putString("FromFragment", "Sorting")
                            Navigation.findNavController(binding.root).navigate(R.id.booksInfoFragment, bundle)
                        }
                    }
                }
                else {
                    // handling error if we get any error.
                }
            }
        })
    }

    fun genresWindow(){ // список блоков с жанрами
        binding.cbtnGenres.setBackgroundResource(R.drawable.cbtn_selector_active_genres)
        binding.cbtnNames.setBackgroundResource(R.drawable.cbtn_selector)
        binding.cbtnAuthors.setBackgroundResource(R.drawable.cbtn_selector)

        binding.sortingList.layoutManager = GridLayoutManager(context, 3)
    }

    fun authorsWindow(){ // список по автору
        binding.cbtnGenres.setBackgroundResource(R.drawable.cbtn_selector)
        binding.cbtnNames.setBackgroundResource(R.drawable.cbtn_selector)
        binding.cbtnAuthors.setBackgroundResource(R.drawable.cbtn_selector_active_authors)
        bundle.putString("SortingState", "Authors")

        binding.sortingList.layoutManager = GridLayoutManager(context, 2)
        getDataFromServer()
    }

    fun namesWindow(namesBlock: Boolean){ // список по названию
        // если функцию вызывает именно 2 БЛОК (список по названию)
        if(namesBlock){
            binding.cbtnGenres.setBackgroundResource(R.drawable.cbtn_selector)
            binding.cbtnAuthors.setBackgroundResource(R.drawable.cbtn_selector)
            binding.cbtnNames.setBackgroundResource(R.drawable.cbtn_selector_active_names)
            bundle.putString("SortingState", "Names")
        }

        binding.sortingList.layoutManager = GridLayoutManager(context, 2)
        getDataFromServer()
    }

    fun clickedGenres(){ // при нажатии на любой блок жанров
        binding.sortingList.adapter = GenresListAdapter(genresList){
            binding.backBtnGenre.visibility = View.VISIBLE
            binding.genreNameText.text = it
            binding.genreNameText.visibility = View.VISIBLE


            binding.sortingList.layoutManager = GridLayoutManager(context, 2)
        }
    }
}