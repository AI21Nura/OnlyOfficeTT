package com.ainsln.feature.documents.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.ainsln.feature.documents.DocumentsScreen
import kotlinx.serialization.Serializable

@Serializable
data class DocumentsDestination(
    val folderId: Long? = null,
    val folderTitle: String? = null
)

fun NavGraphBuilder.documentsDestination(
    navigateToFolder: (Long, String) -> Unit,
    navigateUp: () -> Unit
){
    composable<DocumentsDestination>{
        DocumentsScreen(navigateToFolder, navigateUp)
    }
}

fun NavController.navigateToDocuments(navOptions: NavOptions){
    navigate(DocumentsDestination(), navOptions)
}

fun NavController.navigateToFolder(id: Long, title: String){
    navigate(DocumentsDestination(id, title))
}
