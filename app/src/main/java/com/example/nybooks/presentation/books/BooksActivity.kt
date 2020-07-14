package com.example.nybooks.presentation.books

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nybooks.R
import com.example.nybooks.data.repository.BooksApiDataSource
import com.example.nybooks.presentation.base.BaseActivity
import com.example.nybooks.presentation.details.BookDetailsActivity
import kotlinx.android.synthetic.main.activity_books.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BooksActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        setupToolbar(toolbarMain, R.string.title_books)

//        val viewModel: BooksViewModel = ViewModelProviders.of(this).get(BooksViewModel::class.java)
        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksApiDataSource())
            .create(BooksViewModel::class.java)
        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerBooks) {
                    layoutManager = LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) {book ->
                        val intent = BookDetailsActivity.getIntent(this@BooksActivity, book.title,book.description, book.author)
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let {viewFlipper ->
                vf_book_activity.displayedChild = viewFlipper.first

                viewFlipper.second?.let {errorMessageRedId ->
                    tv_message_exception.text = getString(errorMessageRedId)
                }
            }
        })

        viewModel.getBooks()
    }
}
