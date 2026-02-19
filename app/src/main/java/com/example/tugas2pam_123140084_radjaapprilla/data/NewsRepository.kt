package com.example.tugas2pam_123140084_radjaapprilla.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepository {

    fun getNewsStream(): Flow<List<Article>> = flow {

        val categories = listOf("Teknologi", "Olahraga", "Bisnis")

        var counter = 1

        while (true) {

            val newsList = categories.mapIndexed { index, category ->
                Article(
                    id = index,
                    title = "Berita $category ke-$counter",
                    description = "Ringkasan singkat berita...",
                    category = category
                )
            }

            emit(newsList)
            counter++
            delay(2000)
        }
    }

    // Async ambil detail berita
    suspend fun getDetail(articleId: Int): String {
        delay(1500) // simulasi network
        return "Ini adalah detail lengkap dari berita dengan ID $articleId"
    }
}
