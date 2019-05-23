package com.example.poplibrary

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Spinner
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BookAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var searchView: SearchView
    private lateinit var books: MutableList<Book>

    private lateinit var bookViewModel: BookViewModel

    /**
     * When the activity is created, do this stuff!
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        loadRecyclerView()
        loadSearchFunction()
        loadFilterFunction()
    }

    /**
     * Loads the filter functionality using a spinner.
     */
    private fun loadFilterFunction() {
        val spinner: Spinner = findViewById(R.id.sort_drop_down)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) { }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewAdapter.sort(parent?.getItemAtPosition(position).toString())
            }

        }
    }

    /**
     * Loads the search functionality using a searchView.
     */
    private fun loadSearchFunction() {
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = findViewById(R.id.book_search)
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        // listening to search query text change
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                viewAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                viewAdapter.filter.filter(query)
                return false
            }
        })
    }

    /**
     * Loads the list of books.
     */
    private fun loadRecyclerView() {
        viewManager = LinearLayoutManager(this)
        viewAdapter = BookAdapter(books)

        recyclerView = findViewById<RecyclerView>(R.id.book_recycler_view).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }
    }

    /**
     * Switches context to book add context.
     */
    fun loadBookAddDialog(view: View) {
        val intent: Intent = Intent(this, BookActivity::class.java)
        this.startActivity(intent)
    }
}
