package com.example.tugas2pam_123140084_radjaapprilla

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tugas2pam_123140084_radjaapprilla.viewmodel.NewsViewModel

@Composable
fun NewsScreen(viewModel: NewsViewModel = viewModel()) {

    val newsList by viewModel.newsUiState.collectAsState()
    val detail by viewModel.detail.collectAsState()
    val readCount by viewModel.readCount.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()

    val categories = listOf("Semua", "Teknologi", "Olahraga")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Daftar Berita",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Jumlah Dibaca: $readCount")

        Spacer(modifier = Modifier.height(16.dp))

        // 🔹 Tombol kategori
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            categories.forEach { category ->
                Button(
                    onClick = { viewModel.selectCategory(category) },
                    enabled = selectedCategory != category
                ) {
                    Text(category)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(newsList) { article ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.loadDetail(article.id)
                        }
                        .padding(8.dp)
                ) {
                    Text(article.title)
                    Text(article.description)
                }
                Divider()
            }
        }

        detail?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(8.dp))

            Text("Detail:")
            Spacer(modifier = Modifier.height(4.dp))
            Text(it)

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { viewModel.clearDetail() }) {
                Text("Tutup")
            }
        }
    }
}
