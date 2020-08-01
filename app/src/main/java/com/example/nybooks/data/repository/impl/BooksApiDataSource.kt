package com.example.nybooks.data.repository.impl

import com.example.nybooks.data.NYApiService
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import com.example.nybooks.data.response.BookBodyResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksApiDataSource : BooksRepository {
    override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit) {
        NYApiService.service.getBooks().enqueue(object : Callback<BookBodyResponse> {
            override fun onResponse(call: Call<BookBodyResponse>, response: Response<BookBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val listBooks: MutableList<Book> = mutableListOf()
                        response.body()?.let { bookBodyResponse ->
                            for (result in bookBodyResponse.bookResultsResponse) {
                                val book: Book = result.bookDetailsResponse[0].getBookModel()
                                listBooks.add(book)
                            }
                        }
                        booksResultCallBack.invoke(BooksResult.Success(listBooks))
                    }
                    else -> booksResultCallBack.invoke(BooksResult.ApiError(response.code()))
                }
            }
            override fun onFailure(call: Call<BookBodyResponse>, t: Throwable) {
                booksResultCallBack.invoke(BooksResult.ServerError)
                throw t.fillInStackTrace()
            }
        })
    }
}