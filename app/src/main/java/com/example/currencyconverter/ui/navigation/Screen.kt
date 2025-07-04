package com.example.currencyconverter.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.currencyconverter.R

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val label: Int
) {
    object Currency : Screen(
        route = "currency",
        icon = Icons.Filled.Home,
        label = R.string.screen_currency
    )

    object Exchange : Screen(
        route = "exchange",
        icon = Icons.Filled.Create,
        label = R.string.screen_exchange
    )

    object Transactions : Screen(
        route = "transactions",
        icon = Icons.AutoMirrored.Filled.List,
        label = R.string.screen_transactions
    )
}