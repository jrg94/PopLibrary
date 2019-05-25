package com.therenegadecoder.poplibrary

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.SearchView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: BookAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var searchView: SearchView

    private lateinit var bookViewModel: BookViewModel
    private val newBookActivityRequestCode = 1

    fun csvToBooks(stream: InputStream) : List<Book>{
        val books: MutableList<Book> = mutableListOf()
        val reader = BufferedReader(InputStreamReader(stream))
        val lines = reader.readLines()
        val rows: MutableList<List<String>> = mutableListOf()
        for (line: String in lines) {
            rows.add(line.split(Pattern.compile(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)")))
        }
        rows.removeAt(0)
        for (list: List<String> in rows) {
            val lexileLevel = when (list[2].trim().length) {
                0 -> null
                else -> list[2].substring(0, list[2].trim().length - 1).toIntOrNull()
            }
            val fountasAndPinell = when (list[3].length) {
                0 -> null
                else -> FountasAndPinell.valueOf(list[3].trim())
            }
            books.add(
                Book(
                    title = list[0],
                    author = list[1],
                    lexileLevel = when (lexileLevel) {
                        null -> null
                        else -> Lexile(lexileLevel, Lexile.LexileType.NA)
                    },
                    fountasAndPinell = fountasAndPinell
                )
            )
        }
        return books
    }

    /**
     * When the activity is created, do this stuff!
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)
        bookViewModel.allBooks.observe(this, Observer { books ->
            books?.let { viewAdapter.setBooks(it) }
        })

        val stream: InputStream = resources.openRawResource(R.raw.sample_data)
        val books = csvToBooks(stream)
        books.forEach { bookViewModel.insert(it) }

        loadRecyclerView()
        loadSearchFunction()
        loadFilterFunction()

        val fab = findViewById<FloatingActionButton>(R.id.add_book_button)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, BookActivity::class.java)
            startActivityForResult(intent, newBookActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newBookActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                val book = Book(title = it.getStringExtra(BookActivity.EXTRA_REPLY))
                bookViewModel.insert(book)
            }
        } else {
            Log.d("BOOK", "Failed to create new book")
        }
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
        viewAdapter = BookAdapter()

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
