package com.example.nybooks.presentation.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.nybooks.R
import com.example.nybooks.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.include_toolbar.*

class BookDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)

        setupToolbar(toolbarMain, R.string.title_details_books, hasBackBtn = true)

        buildFields()

    }

    private fun buildFields() {
        tv_title.text = intent.getStringExtra(EXTRA_TITLE)
        tv_description.text = intent.getStringExtra(EXTRA_DESCRIPTION)
        tv_author.text = intent.getStringExtra(EXTRA_AUTHOR)
    }

    companion object {

        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"
        private const val EXTRA_AUTHOR = "EXTRA_AUTHOR"


        fun getIntent(context: Context, title: String, description: String, author: String ): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("EXTRA_TITLE", title)
                putExtra("EXTRA_DESCRIPTION", description)
                putExtra("EXTRA_AUTHOR", author)
            }
        }
    }
}
