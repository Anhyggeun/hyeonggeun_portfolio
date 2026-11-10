package com.example.dongnemarket.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnemarket.data.ProductRepository
import com.example.dongnemarket.data.local.ProductEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ProductListUiState(
    val isLoading: Boolean = true,
    val products: List<ProductEntity> = emptyList(),
    val categories: List<String> = emptyList(),
    val selectedCategory: String? = null
)

class ProductListViewModel(private val repository: ProductRepository) : ViewModel() {

    private val selectedCategory = MutableStateFlow<String?>(null)

    val uiState: StateFlow<ProductListUiState> = combine(
        repository.observeAll(),
        selectedCategory
    ) { all, selected ->
        ProductListUiState(
            isLoading = false,
            products = if (selected == null) all else all.filter { it.category == selected },
            categories = all.map { it.category }.distinct(),
            selectedCategory = selected
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ProductListUiState()
    )

    fun selectCategory(category: String?) {
        selectedCategory.value = category
    }

    fun toggleLike(id: Long) {
        viewModelScope.launch { repository.toggleLike(id) }
    }
}