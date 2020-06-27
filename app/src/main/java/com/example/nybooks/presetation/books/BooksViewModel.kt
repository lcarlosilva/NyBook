package com.example.nybooks.presetation.books

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nybooks.data.ApiService
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksViewModel : ViewModel() {

    val booksLiveData: MutableLiveData<List<Book>> = MutableLiveData()

    fun getBooks() {
//        booksLiveData.value = getFakeRequestBooks()
        ApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                if (response.isSuccessful) {
                    val listBooks: MutableList<Book> = mutableListOf()
                    response.body()?.let { bookBodyResponse ->
                        for (result in bookBodyResponse.bookResultsResponse) {
                            val book: Book = Book(
                                title = result.bookDetailsResponse[0].title,
                                author = result.bookDetailsResponse[0].author,
                                description = result.bookDetailsResponse[0].description
                            )
                            listBooks.add(book)
                        }
                    }
                    booksLiveData.value = listBooks
                }
            }

            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                throw t.fillInStackTrace()
            }
        })
    }

    /*fun getFakeRequestBooks() : List<Book> {
        return listOf<Book>(
            Book("Titulo Um", "Autor A"),
            Book("Titulo Dois", "Autor B"),
            Book("Titulo Três", "Autor C")
        )
    }*/

}