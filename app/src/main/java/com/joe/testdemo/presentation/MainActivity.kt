package com.joe.testdemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.joe.testdemo.presentation.theme.TestDemoTheme
import com.joe.testdemo.presentation.transactiondetail.navigateToDetail
import com.joe.testdemo.presentation.transactiondetail.transactionDetailScreen
import com.joe.testdemo.presentation.transactionlist.TransactionListRoute
import com.joe.testdemo.presentation.transactionlist.transactionListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            TestDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        NavHost(
                            navController = navController,
                            startDestination = TransactionListRoute
                        ) {
                            transactionListScreen(
                                onNavigateToTransactionDetail = { id ->
                                    navController.navigateToDetail(id)
                                }
                            )
                            transactionDetailScreen()
                        }
                    }
                }
            }
        }
    }
}