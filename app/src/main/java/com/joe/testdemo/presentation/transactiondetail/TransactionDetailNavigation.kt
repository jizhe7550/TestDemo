package com.joe.testdemo.presentation.transactiondetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class TransactionDetailRoute(val id: Int)

fun NavGraphBuilder.transactionDetailScreen() {
    composable<TransactionDetailRoute> {
        TransactionDetailScreen()
    }
}

fun NavController.navigateToDetail(id: Int, navOptions: NavOptions? = null) {
    this.navigate(navOptions = navOptions, route = TransactionDetailRoute(id))
}