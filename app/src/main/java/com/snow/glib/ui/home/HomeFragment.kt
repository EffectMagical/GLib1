package com.snow.glib.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import com.snow.glib.R
import com.snow.glib.databinding.FragmentHomeBinding
import com.snow.glib.ui.BookListAdapter
import com.snow.glib.ui.BookListDataItem
import java.util.Locale


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var homeBookList = arrayListOf<BookListDataItem>()
    private var adapter: BookListAdapter = BookListAdapter(listOf()){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeBookList.clear()
        getDataFromServer()

        binding.search.clearFocus()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(newText: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    filter(newText)
                }
                return false
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

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

                            homeBookList.add(BookListDataItem(elemId, cover, name, author, genres))
                        }
                        adapter = BookListAdapter(homeBookList) {
                            val bundle = Bundle()
                            bundle.putString("bookId", it)
                            bundle.putString("FromFragment", "Home")
                            Navigation.findNavController(binding.root).navigate(R.id.booksInfoFragment, bundle)
                        }
                        binding.homeList.adapter = adapter
                    }
                }
                else {
                // handling error if we get any error.
                }
            }
            })
        }

    private fun filter(text: String){
        val filterListData = mutableListOf<BookListDataItem>()
        for (item in homeBookList){
            if (item.name.toLowerCase().contains(text.lowercase(Locale.getDefault()))){
                filterListData.add(item)
            }
        }
        if (filterListData.isEmpty()){
//            Toast.makeText(requireContext(), "По вашему запросу ничего не найдено", Toast.LENGTH_SHORT).show()
        }
        else{
            adapter.filterList(filterListData)
        }
    }
    }