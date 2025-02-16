package com.ainsln.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ainsln.feature.profile.ProfileScreen
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileDestination(){
    composable<ProfileDestination> {
        ProfileScreen()
    }
}

fun NavController.navigateToProfile(navOptions: NavOptions){
    navigate(ProfileDestination, navOptions)
}
