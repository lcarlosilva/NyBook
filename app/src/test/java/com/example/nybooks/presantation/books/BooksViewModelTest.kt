package com.example.nybooks.presantation.books

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nybooks.R
import com.example.nybooks.data.BooksResult
import com.example.nybooks.data.model.Book
import com.example.nybooks.data.repository.BooksRepository
import com.example.nybooks.presentation.books.BooksViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BooksViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var booksLiveDataObserver: Observer<List<Book>>

    @Mock
    private lateinit var viewFlipperLiveDataObserver: Observer<Pair<Int, Int?>>

    private lateinit var viewModel: BooksViewModel

    /*@Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }*/

    @Test
    fun `when view model getBooks success then set booksLiveData`() {
        //Arrange
        val books = listOf(
            Book("title 1", "author 1", "description 1")
        )
        val resultSuccess = MockRespository(BooksResult.Success(books))
        viewModel = BooksViewModel(resultSuccess)
        viewModel.booksLiveData.observeForever(booksLiveDataObserver)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(booksLiveDataObserver).onChanged(books)
        verify(viewFlipperLiveDataObserver).onChanged(Pair(1, null))
    }

    @Test
    fun `when view model getBooks server error then set viewFlipperLiveData`() {
        //Arrange
        val resultServerError = MockRespository(BooksResult.ServerError)
        viewModel = BooksViewModel(resultServerError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_500))
    }

    @Test
    fun `when view model getBooks error statusCode like 401 then set viewFlipperLiveData`() {
        //Arrange
        val resultError = MockRespository(BooksResult.ApiError(401))
        viewModel = BooksViewModel(resultError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_401))
    }

    @Test
    fun `when view model getBooks error statusCode like 400 then set viewFlipperLiveData`() {
        //Arrange
        val resultError = MockRespository(BooksResult.ApiError(400))
        viewModel = BooksViewModel(resultError)
        viewModel.viewFlipperLiveData.observeForever(viewFlipperLiveDataObserver)

        //Act
        viewModel.getBooks()

        //Assert
        verify(viewFlipperLiveDataObserver).onChanged(Pair(2, R.string.books_error_400))
        Assert.assertEquals(viewModel.viewFlipperLiveData.value, Pair(2, R.string.books_error_400))
    }

    class MockRespository(private val result: BooksResult) : BooksRepository {
        override fun getBooks(booksResultCallBack: (result: BooksResult) -> Unit) {
            booksResultCallBack(result)
        }

    }
}