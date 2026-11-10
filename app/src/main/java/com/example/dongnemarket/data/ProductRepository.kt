package com.example.dongnemarket.data

import com.example.dongnemarket.data.local.ProductDao
import com.example.dongnemarket.data.local.ProductEntity
import kotlinx.coroutines.flow.Flow

class ProductRepository(private val dao: ProductDao) {
    fun observeAll(): Flow<List<ProductEntity>> = dao.observeAll()

    fun observeById(id: Long): Flow<ProductEntity?> = dao.observeById(id)

    suspend fun add(product: ProductEntity) {
        dao.insert(product)
    }

    suspend fun toggleLike(id: Long) {
        dao.toggleLike(id)
    }
}