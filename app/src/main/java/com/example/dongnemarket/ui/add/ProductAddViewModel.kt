package com.example.dongnemarket.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dongnemarket.data.ProductRepository
import com.example.dongnemarket.data.local.ProductEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddFormState(
    val title: String = "",
    val price: String = "",
    val description: String = "",
    val category: String = "디지털기기",
    val location: String = "송도동"
) {
    val isValid: Boolean
        get() = title.isNotBlank() && price.isNotEmpty() && description.isNotBlank() && location.isNotBlank()
}

class ProductAddViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _form = MutableStateFlow(AddFormState())
    val form: StateFlow<AddFormState> = _form.asStateFlow()

    fun onTitleChange(value: String) = _form.update { it.copy(title = value) }

    // 숫자만 허용, 9자리까지 (Int 범위 안전)
    fun onPriceChange(value: String) =
        _form.update { it.copy(price = value.filter { c -> c.isDigit() }.take(9)) }

    fun onDescriptionChange(value: String) = _form.update { it.copy(description = value) }
    fun onCategoryChange(value: String) = _form.update { it.copy(category = value) }
    fun onLocationChange(value: String) = _form.update { it.copy(location = value) }

    fun save(onSaved: () -> Unit) {
        val f = _form.value
        if (!f.isValid) return
        viewModelScope.launch {
            repository.add(
                ProductEntity(
                    title = f.title.trim(),
                    price = f.price.toInt(),
                    description = f.description.trim(),
                    category = f.category,
                    location = f.location.trim(),
                    imageUrl = "https://picsum.photos/seed/${System.currentTimeMillis()}/400/400"
                )
            )
            onSaved()
        }
    }
}