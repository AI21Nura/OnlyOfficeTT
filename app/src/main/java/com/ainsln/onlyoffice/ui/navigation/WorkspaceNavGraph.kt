package com.ainsln.onlyoffice.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.ainsln.feature.documents.navigation.DocumentsDestination
import com.ainsln.feature.documents.navigation.documentsDestination
import com.ainsln.feature.documents.navigation.navigateToFolder
import com.ainsln.feature.profile.navigation.profileDestination
import com.ainsln.feature.room.navigation.navigateToRoomContent
import com.ainsln.feature.room.navigation.roomsGraph
import com.ainsln.feature.trash.navigation.trashDestination
import kotlinx.serialization.Serializable

@Serializable
data object WorkspaceGraph

fun NavGraphBuilder.workspaceGraph(navController: NavController){
    navigation<WorkspaceGraph>(
        startDestination = DocumentsDestination()
    ){
        documentsDestination(
            navigateToFolder = navController::navigateToFolder,
            navigateUp = navController::popBackStack
        )
        roomsGraph(
            navigateToRoom = navController::navigateToRoomContent,
            navigateUp = navController::popBackStack
        )
        trashDestination()
        profileDestination()
    }
}
