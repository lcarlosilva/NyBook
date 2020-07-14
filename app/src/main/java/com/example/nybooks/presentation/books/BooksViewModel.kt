package com.example.nybooks.presentation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nybooks.R
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import java.lang.IllegalArgumentException

class BooksViewModel(val dataSource: BooksRepository) : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getBooks() {
        dataSource.getBooks { result: BooksResult ->
            when (result) {
                is BooksResult.Success -> {
                    booksLiveData.value = result.books
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                }
                is BooksResult.ApiError -> {
                    if (result.statusCode == 401) {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                    } else {
                        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400)
                    }
                }
                is BooksResult.ServerError -> {
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500)
                }
            }
        }
//        booksLiveData.value = getFakeRequestBooks()
        /*ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                if (response.isSuccessful) {
                    val listBooks: MutableList<Book> = mutableListOf()
                    response.body()?.let { bookBodyResponse ->
                        for (result in bookBodyResponse.bookResultsResponse) {
                            val book: Book = result.bookDetailsResponse[0].getBookModel()
                                *//*Book(
                                title = result.bookDetailsResponse[0].title,
                                author = result.bookDetailsResponse[0].author,
                                description = result.bookDetailsResponse[0].description
                            )*//*
                            listBooks.add(book)
                        }
                    }
                    booksLiveData.value = listBooks
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
                } else if (response.code() == 401) {
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_401)
                } else {
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_400)
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, R.string.books_error_500)
                throw t.fillInStackTrace()

            }
        })*/
    }

    class ViewModelFactory(val dataSource: BooksRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
                return BooksViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    /*fun getFakeRequestBooks() : List<Book> {
        return listOf<Book>(
            Book("Titulo Um", "Autor A"),
            Book("Titulo Dois", "Autor B"),
            Book("Titulo Três", "Autor C")
        )
    }*/

}