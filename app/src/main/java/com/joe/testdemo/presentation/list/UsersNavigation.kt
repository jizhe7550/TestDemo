package com.joe.testdemo.presentation.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object UsersRoute

fun NavGraphBuilder.usersScreen(
    onNavigateToUserScreen: (Int) -> Unit
) {
    composable<UsersRoute> {
        UsersScreen(
            onNavigateToUserScreen = onNavigateToUserScreen
        )
    }
}