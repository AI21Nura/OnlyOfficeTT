package com.ainsln.feature.room.navigation

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * This file follows the approach described in
 * [Type Safety in Navigation Compose]
 * (https://medium.com/mercadona-tech/type-safety-in-navigation-compose-23c03e3d74a5)
 */

@Serializable
@Parcelize
data class RoomContentArgs(
    val roomTitle: String,
    val folderId: Long,
    val folderTitle: String? = null
) : Parcelable

inline fun <reified T : Parcelable> parcelableType(
    isNullableAllowed: Boolean = true,
    json: Json = Json
) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
    override fun get(bundle: Bundle, key: String) =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, T::class.java)
        } else {
            @Suppress("DEPRECATION")
            bundle.getParcelable(key)
        }

    override fun parseValue(value: String): T = json.decodeFromString(value)

    override fun serializeAsValue(value: T): String = json.encodeToString(value)

    override fun put(bundle: Bundle, key: String, value: T) = bundle.putParcelable(key, value)
}
