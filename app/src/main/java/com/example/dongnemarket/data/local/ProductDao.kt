package com.example.dongnemarket.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products ORDER BY createdAt DESC")
    fun observeAll(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE id = :id")
    fun observeById(id: Long): Flow<ProductEntity?>

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count(): Int

    @Insert
    suspend fun insert(product: ProductEntity): Long

    @Insert
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("UPDATE products SET isLiked = NOT isLiked WHERE id = :id")
    suspend fun toggleLike(id: Long)
}