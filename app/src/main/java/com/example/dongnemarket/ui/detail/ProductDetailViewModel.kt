package com.example.dongnemarket.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnemarket.data.ProductRepository
import com.example.dongnemarket.data.local.ProductEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: ProductRepository
) : ViewModel() {

    private val productId: Long = checkNotNull(savedStateHandle["id"])

    val product: StateFlow<ProductEntity?> = repository.observeById(productId)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun toggleLike() {
        viewModelScope.launch { repository.toggleLike(productId) }
    }
}