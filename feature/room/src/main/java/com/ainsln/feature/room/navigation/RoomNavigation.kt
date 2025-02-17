package com.ainsln.feature.room.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.ainsln.feature.room.content.RoomContentScreen
import com.ainsln.feature.room.overview.RoomOverviewScreen
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

@Serializable
data object RoomGraph {
    const val ROUTE: String = "com.ainsln.feature.room.navigation.RoomGraph"
}
@Serializable
data object RoomDestination
@Serializable
data class RoomContentDestination(val args: RoomContentArgs)

fun NavGraphBuilder.roomsGraph(
    navigateToRoom: (RoomContentArgs) -> Unit,
    navigateUp: () -> Unit
){
    navigation<RoomGraph>(
        startDestination = RoomDestination
    ){
        composable<RoomDestination>{
            RoomOverviewScreen(navigateToRoom)
        }

        composable<RoomContentDestination>(
            typeMap = mapOf(typeOf<RoomContentArgs>() to parcelableType<RoomContentArgs>())
        ){
            RoomContentScreen(
                navigateToContent = navigateToRoom,
                navigateUp = navigateUp
            )
        }
    }
}

fun NavController.navigateToRooms(navOptions: NavOptions){
    navigate(RoomDestination, navOptions)
}

fun NavController.navigateToRoomContent(contentArgs: RoomContentArgs){
    navigate(RoomContentDestination(contentArgs))
}
