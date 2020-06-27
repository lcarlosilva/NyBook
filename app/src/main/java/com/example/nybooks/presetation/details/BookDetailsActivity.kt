package com.example.nybooks.presetation.details

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nybooks.R
import kotlinx.android.synthetic.main.activity_book_details.*
import kotlinx.android.synthetic.main.activity_books.*

class BookDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_details)
        setSupportActionBar(toolbarMain)

        tv_title.text = intent.getStringExtra(EXTRA_TITLE)
        tv_description.text = intent.getStringExtra(EXTRA_DESCRIPTION)

    }

    companion object {

        private const val EXTRA_TITLE = "EXTRA_TITLE"
        private const val EXTRA_DESCRIPTION = "EXTRA_DESCRIPTION"


        fun getIntent(context: Context, title: String, description: String): Intent {
            return Intent(context, BookDetailsActivity::class.java).apply {
                putExtra("EXTRA_TITLE", title)
                putExtra("EXTRA_DESCRIPTION", description)
            }
        }
    }
}
