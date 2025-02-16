package com.ainsln.feature.auth.state

data class AuthFormState(
    val portal: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val inputErrors: Map<Field, ValidationError> = emptyMap()
)

enum class Field { PORTAL, EMAIL, PASSWORD }

sealed class ValidationError {
    data object Empty : ValidationError()
    data object InvalidPortalFormat : ValidationError()
    data object InvalidEmailFormat : ValidationError()
}

