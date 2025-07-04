package com.example.currencyconverter.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.ui.navigation.ExchangeScreen
import com.example.currencyconverter.ui.navigation.Screen
import com.example.currencyconverter.ui.screens.currency.CurrencyScreen

@Composable
fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                listOf(
                    Screen.Currency,
                    Screen.Exchange,
                    Screen.Transactions
                ).forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(LocalContext.current.getText(screen.label).toString()) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Очистка стека до корневого элемента
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Избегаем множественных копий одного экрана
                                launchSingleTop = true
                                // Восстанавливаем состояние
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Currency.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Currency.route) { CurrencyScreen() }
            composable(Screen.Exchange.route) { ExchangeScreen() }
            composable(Screen.Transactions.route) { TransactionsScreen() }
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun HomeScreenPreview() {
    HomeScreen()
}