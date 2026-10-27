package com.tecsup.bookshelf.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.tecsup.bookshelf.BookshelfApplication
import com.tecsup.bookshelf.data.Book
import com.tecsup.bookshelf.data.BooksRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface BookshelfUiState {
    object Loading : BookshelfUiState
    data class Success(val books: List<Book>) : BookshelfUiState
    data class Error(val mensaje: String) : BookshelfUiState
}

class BookshelfViewModel(private val booksRepository: BooksRepository) : ViewModel() {

    var uiState: BookshelfUiState by mutableStateOf(BookshelfUiState.Loading)
        private set

    var query by mutableStateOf("android")
        private set

    init {
        buscar()
    }

    fun onQueryChange(nuevo: String) {
        query = nuevo
    }

    fun buscar(texto: String = query) {
        query = texto
        viewModelScope.launch {
            uiState = BookshelfUiState.Loading
            uiState = try {
                BookshelfUiState.Success(booksRepository.searchBooks(texto.trim()))
            } catch (e: IOException) {
                BookshelfUiState.Error("Sin conexión con Open Library")
            } catch (e: HttpException) {
                BookshelfUiState.Error("El servidor respondió ${e.code()}")
            }
        }
    }

    fun libro(id: String): Book? =
        (uiState as? BookshelfUiState.Success)?.books?.find { it.id == id }

    companion object {
        // El ViewModel recibe el repositorio desde el contenedor de la aplicacion
        val Factory: androidx.lifecycle.ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[APPLICATION_KEY] as BookshelfApplication
                BookshelfViewModel(app.container.booksRepository)
            }
        }
    }
}
