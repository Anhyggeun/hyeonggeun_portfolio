package com.example.dongnemarket.ui.add

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dongnemarket.ui.AppViewModelProvider

private val categories = listOf("디지털기기", "가구", "도서", "스포츠", "게임", "생활가전", "의류", "기타")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductAddScreen(
    onBack: () -> Unit,
    viewModel: ProductAddViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val form by viewModel.form.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("상품 등록") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "뒤로 가기")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = form.title,
                onValueChange = viewModel::onTitleChange,
                label = { Text("제목") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = form.price,
                onValueChange = viewModel::onPriceChange,
                label = { Text("가격") },
                suffix = { Text("원") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Text("카테고리", style = MaterialTheme.typography.labelLarge)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories) { category ->
                    FilterChip(
                        selected = form.category == category,
                        onClick = { viewModel.onCategoryChange(category) },
                        label = { Text(category) }
                    )
                }
            }

            OutlinedTextField(
                value = form.location,
                onValueChange = viewModel::onLocationChange,
                label = { Text("거래 희망 동네") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = form.description,
                onValueChange = viewModel::onDescriptionChange,
                label = { Text("상품 설명") },
                minLines = 4,
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.save(onSaved = onBack) },
                enabled = form.isValid,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("등록하기")
            }
        }
    }
}