package com.example.dongnemarket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val price: Int,
    val description: String,
    val category: String,
    val location: String,
    val imageUrl: String?,
    val isLiked: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)