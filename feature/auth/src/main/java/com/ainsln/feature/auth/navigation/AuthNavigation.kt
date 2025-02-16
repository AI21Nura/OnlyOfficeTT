package com.ainsln.feature.auth.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ainsln.feature.auth.AuthScreen
import kotlinx.serialization.Serializable

@Serializable data object AuthDestination

fun NavGraphBuilder.authDestination(
    showSnackbar: suspend (String) -> Unit,
){
    composable<AuthDestination> {
        AuthScreen(showSnackbar)
    }
}

