package com.example.dongnemarket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dongnemarket.ui.add.ProductAddScreen
import com.example.dongnemarket.ui.detail.ProductDetailScreen
import com.example.dongnemarket.ui.list.ProductListScreen
import com.example.dongnemarket.ui.theme.DangenMarketTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DangenMarketTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        ProductListScreen(
                            onProductClick = { id -> navController.navigate("detail/$id") },
                            onAddClick = { navController.navigate("add") }
                        )
                    }
                    composable("add") {
                        ProductAddScreen(onBack = { navController.popBackStack() })
                    }
                    composable(
                        route = "detail/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.LongType })
                    ) {
                        ProductDetailScreen(onBack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}