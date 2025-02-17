package com.ainsln.onlyoffice.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.ainsln.core.ui.component.LoadingScreen
import com.ainsln.core.ui.state.UiState
import com.ainsln.feature.auth.navigation.AuthDestination
import com.ainsln.feature.auth.navigation.authDestination
import com.ainsln.onlyoffice.ui.navigation.AppNavigationBar
import com.ainsln.onlyoffice.ui.navigation.WorkspaceGraph
import com.ainsln.onlyoffice.ui.navigation.workspaceGraph

@Composable
fun AppContent(isAuthorizedState: UiState<Boolean>){
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    when(isAuthorizedState){
        is UiState.Success -> {
            val isAuthorized = isAuthorizedState.data
            val startDestination: Any = if (isAuthorized) WorkspaceGraph else AuthDestination

            Scaffold(
                snackbarHost = { SnackbarHost(snackbarHostState) },
                bottomBar = { if (isAuthorized) AppNavigationBar(navController) }
            ) { innerPadding ->
                Box(
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .consumeWindowInsets(innerPadding)
                ){
                    NavHost(
                        navController = navController,
                        startDestination = startDestination
                    ){
                        authDestination(
                            showSnackbar = { msg -> snackbarHostState.showSnackbar(msg) }
                        )
                        workspaceGraph(navController)
                    }
                }
            }
        }
        else -> LoadingScreen()
    }
}
