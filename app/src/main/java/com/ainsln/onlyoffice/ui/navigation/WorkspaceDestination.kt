package com.ainsln.onlyoffice.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.ainsln.feature.documents.navigation.DocumentsDestination
import com.ainsln.feature.profile.navigation.ProfileDestination
import com.ainsln.feature.room.navigation.RoomGraph
import com.ainsln.feature.trash.navigation.TrashDestination
import com.ainsln.onlyoffice.R
import kotlin.reflect.KClass

enum class WorkspaceDestination(
    @StringRes val titleResId: Int,
    val icon: ImageVector,
    val route: KClass<*>
) {
    DOCUMENTS(
        titleResId = R.string.documents,
        icon = Icons.Default.Description,
        route = DocumentsDestination::class
    ),
    ROOMS(
        titleResId = R.string.rooms,
        icon = Icons.Default.MeetingRoom,
        route = RoomGraph::class
    ){

    },
    TRASH(
        titleResId = R.string.trash,
        icon = Icons.Default.Delete,
        route = TrashDestination::class
    ),
    PROFILE(
        titleResId = R.string.profile,
        icon = Icons.Default.Person,
        route = ProfileDestination::class
    )
}
