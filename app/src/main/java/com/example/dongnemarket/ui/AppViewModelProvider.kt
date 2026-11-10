package com.example.dongnemarket.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.dongnemarket.MarketApp
import com.example.dongnemarket.ui.list.ProductListViewModel
import androidx.lifecycle.createSavedStateHandle
import com.example.dongnemarket.ui.detail.ProductDetailViewModel
import com.example.dongnemarket.ui.add.ProductAddViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            ProductListViewModel(marketApp().repository)
        }
        initializer {
            ProductDetailViewModel(this.createSavedStateHandle(), marketApp().repository)
        }
        initializer {
            ProductAddViewModel(marketApp().repository)
        }

    }
}

fun CreationExtras.marketApp(): MarketApp =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MarketApp