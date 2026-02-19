package com.example.tugas2pam_123140084_radjaapprilla.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tugas2pam_123140084_radjaapprilla.data.Article
import com.example.tugas2pam_123140084_radjaapprilla.data.NewsRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {

    private val repository = NewsRepository()

    // 🔹 StateFlow kategori
    private val _selectedCategory = MutableStateFlow("Semua")
    val selectedCategory: StateFlow<String> = _selectedCategory

    // 🔹 StateFlow jumlah dibaca
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount

    // 🔹 Flow berita + transform + filter
    val newsUiState: StateFlow<List<Article>> =
        combine(
            repository.getNewsStream()
                .map { list ->
                    list.map { it.copy(title = it.title.uppercase()) }
                },
            _selectedCategory
        ) { news, category ->
            if (category == "Semua") news
            else news.filter { it.category == category }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // 🔹 Detail async
    private val _detail = MutableStateFlow<String?>(null)
    val detail: StateFlow<String?> = _detail

    fun loadDetail(articleId: Int) {
        viewModelScope.launch {
            val result = repository.getDetail(articleId)
            _detail.value = result
            _readCount.value++
        }
    }

    fun clearDetail() {
        _detail.value = null
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }
}
