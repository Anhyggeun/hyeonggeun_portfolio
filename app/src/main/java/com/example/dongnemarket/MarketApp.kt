package com.example.dongnemarket

import android.app.Application
import androidx.room.Room
import com.example.dongnemarket.data.local.AppDatabase
import com.example.dongnemarket.data.local.SeedData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.dongnemarket.data.ProductRepository

class MarketApp : Application() {
    lateinit var database: AppDatabase
        private set
    val repository by lazy { ProductRepository(database.productDao()) }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(this, AppDatabase::class.java, "market.db").build()

        CoroutineScope(Dispatchers.IO).launch {
            val dao = database.productDao()
            if (dao.count() == 0) dao.insertAll(SeedData.products)
        }
    }
}