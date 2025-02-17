package com.ainsln.feature.trash.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ainsln.feature.trash.TrashScreen
import kotlinx.serialization.Serializable

@Serializable
data object TrashDestination

fun NavGraphBuilder.trashDestination(){
    composable<TrashDestination> {
        TrashScreen()
    }
}

fun NavController.navigateToTrash(navOptions: NavOptions){
    navigate(TrashDestination, navOptions)
}
