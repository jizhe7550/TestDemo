package com.joe.testdemo.presentation.transactionlist

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object TransactionListRoute

fun NavGraphBuilder.transactionListScreen(
    onNavigateToTransactionDetail: (Int) -> Unit,
) {
    composable<TransactionListRoute> {
        TransactionListScreen(
            onNavigateToTransactionDetail = onNavigateToTransactionDetail,
        )
    }
}

fun NavController.navigateToList(navOptions: NavOptions? = null) {
    this.navigate(navOptions = navOptions, route = TransactionListRoute)
}