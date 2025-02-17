package com.ainsln.onlyoffice.ui.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.ainsln.feature.documents.navigation.navigateToDocuments
import com.ainsln.feature.profile.navigation.navigateToProfile
import com.ainsln.feature.room.navigation.RoomGraph
import com.ainsln.feature.room.navigation.navigateToRooms
import com.ainsln.feature.trash.navigation.navigateToTrash
import kotlin.reflect.KClass

@Composable
fun AppNavigationBar(
    navController: NavController
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        WorkspaceDestination.entries.forEach { destination ->
            NavigationBarItem(
                selected = currentDestination.isRouteInHierarchy(destination.route),
                onClick = { navController.navigateToTopDestination(currentDestination, destination) },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.titleResId)
                    )
                },
                label = { Text(stringResource(destination.titleResId)) }
            )
        }
    }
}

internal fun NavController.navigateToTopDestination(currentDestination: NavDestination?, destination: WorkspaceDestination) {
    val navOptions = navOptions {
        popUpTo(findPopUpToTarget(currentDestination)) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

    when (destination) {
        WorkspaceDestination.DOCUMENTS -> navigateToDocuments(navOptions)
        WorkspaceDestination.ROOMS -> navigateToRooms(navOptions)
        WorkspaceDestination.TRASH -> navigateToTrash(navOptions)
        WorkspaceDestination.PROFILE -> navigateToProfile(navOptions)
    }
}

internal fun NavDestination?.isRouteInHierarchy(route: KClass<*>) =
    this?.hierarchy?.any { it.hasRoute(route) } ?: false

/**
 * If the current destination is part of nested RoomGraph, it returns its route.
 * Otherwise, it returns the start destination of the WorkspaceGraph.
 */
internal fun NavController.findPopUpToTarget(currentDestination: NavDestination?): String{
    return if (currentDestination.isRouteInHierarchy(WorkspaceDestination.ROOMS.route)){
        RoomGraph.ROUTE
    } else {
        graph.findStartDestination().route!!
    }
}
