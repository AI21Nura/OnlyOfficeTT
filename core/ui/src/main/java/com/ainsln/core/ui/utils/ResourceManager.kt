package com.ainsln.core.ui.utils

import android.content.Context
import androidx.annotation.StringRes
import com.ainsln.core.common.exception.AppException
import com.ainsln.core.ui.R
import com.ainsln.core.ui.utils.ResourceManager.Companion.getErrorStringId
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourceManager {
    fun getString(@StringRes stringId: Int, vararg args: Any): String
    fun getErrorString(e: AppException): String

    companion object {
        fun getErrorStringId(e: AppException): Int {
            return when (e) {
                is AppException.AuthException -> R.string.error_auth
                is AppException.NoConnectionException -> R.string.error_no_connection
                is AppException.InvalidResponseException -> R.string.error_invalid_response
                is AppException.NotFoundException -> R.string.error_not_found
                is AppException.UnknownException -> R.string.error_unknown
            }
        }
    }
}

internal class BaseResourceManager @Inject constructor(
    @ApplicationContext private val context: Context
) : ResourceManager {

    override fun getString(stringId: Int, vararg args: Any): String {
        return context.getString(stringId, *args)
    }

    override fun getErrorString(e: AppException): String {
        val errorId = getErrorStringId(e)
        return context.getString(errorId)
    }
}
