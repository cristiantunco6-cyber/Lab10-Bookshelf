package com.tecsup.bookshelf.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tecsup.bookshelf.data.Book

@Composable
fun BookDetailScreen(book: Book?) {
    if (book == null) {
        Text("Libro no encontrado", modifier = Modifier.padding(16.dp))
        return
    }
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = book.coverUrl("L"),
            contentDescription = book.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.width(220.dp).height(330.dp).clip(RoundedCornerShape(16.dp))
        )
        Spacer(Modifier.height(16.dp))
        Text(book.title, fontSize = 24.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Text(
            book.authors?.joinToString(", ") ?: "Autor desconocido",
            fontSize = 16.sp, color = Cafe, textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Dato("Primera publicación", book.firstPublishYear?.toString() ?: "-")
        Dato("Ediciones", book.editionCount?.toString() ?: "-")
        Dato("Id Open Library", book.id)
        book.subjects?.take(6)?.let {
            Spacer(Modifier.height(8.dp))
            Text("Temas", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth())
            Text(it.joinToString(" · "), fontSize = 14.sp, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
private fun Dato(titulo: String, valor: String) {
    Column(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
        Text(titulo, fontSize = 12.sp, color = Cafe)
        Text(valor, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
    }
}
