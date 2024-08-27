package com.snow.glib.ui.books_info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ktx.findAll
import com.snow.glib.R
import com.snow.glib.databinding.FragmentBooksInfoBinding
import com.snow.glib.ui.BookListAdapter
import com.snow.glib.ui.BookListDataItem

class BooksInfoFragment : Fragment() {

    private lateinit var binding: FragmentBooksInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataFromServer()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBooksInfoBinding.inflate(inflater, container, false)

        binding.backBtnBookInfo.setOnClickListener {
            if (arguments?.getString("FromFragment") == "Home") Navigation.findNavController(binding.root)
                .navigate(R.id.homeFragment)
            if (arguments?.getString("FromFragment") == "Sorting") Navigation.findNavController(
                binding.root
            ).navigate(R.id.sortingFragment,)
            if (arguments?.getString("FromFragment") == "Favourite") Navigation.findNavController(
                binding.root
            ).navigate(R.id.favouriteFragment)
            if (arguments?.getString("FromFragment") == "Booking") Navigation.findNavController(
                binding.root
            ).navigate(R.id.bookingFragment)
            if (arguments?.getString("FromFragment") == "Reading") Navigation.findNavController(
                binding.root
            ).navigate(R.id.readingFragment)
        }

        return binding.root
    }

    private fun getDataFromServer() {
        val infoBookList = mutableListOf<BooksInfoItem>()

        val query: ParseQuery<ParseObject> = ParseQuery.getQuery<ParseObject>("Book")

        //.get(arguments?.getString("bookId"))
        //.whereEqualTo("objectId", arguments?.getString("bookId"))
        query.findInBackground { objects, e ->
            if (e == null) {
                if (objects != null) {
                    for (o: ParseObject in objects) {
                        if (o.objectId == arguments?.getString("bookId")) {

                            val cover = o.getString("CoverTop").toString()
                            val name = o.getString("Name").toString()
                            val author = o.getString("Author").toString()
                            val genres = o.getString("Genres").toString()
                            val year = o.getInt("Year").toString()
                            val description = o.getString("Description").toString()


                            infoBookList.add(
                                BooksInfoItem(
                                    cover,
                                    name,
                                    author,
                                    year,
                                    genres,
                                    description
                                )
                            )

                            binding.booksInfoList.layoutManager =
                                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                            binding.booksInfoList.adapter = BooksInfoListAdapter(infoBookList)
                            break
                        }

                    }
                }

            }
        }
    }
}
