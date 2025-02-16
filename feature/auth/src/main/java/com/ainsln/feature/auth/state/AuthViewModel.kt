package com.ainsln.feature.auth.state

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ainsln.core.common.result.DataResult
import com.ainsln.core.data.repository.auth.AuthRepository
import com.ainsln.core.model.auth.AuthData
import com.ainsln.core.ui.utils.ResourceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthFormState())
    val uiState = _uiState.asStateFlow()

    private val _uiEvents = Channel<AuthUiEvent>()
    val uiEvents = _uiEvents.receiveAsFlow()

    fun onValueChange(field: Field, value: String) {
        _uiState.update { current ->
            val newErrorsMap = current.inputErrors - field
            when (field) {
                Field.PORTAL -> current.copy(portal = value, inputErrors = newErrorsMap)
                Field.EMAIL -> current.copy(email = value, inputErrors = newErrorsMap)
                Field.PASSWORD -> current.copy(password = value, inputErrors = newErrorsMap)
            }
        }
    }

    fun onLoginClick(){
        switchIsLoading()
        val form = uiState.value
        if (!validateForm(form)) {
            switchIsLoading()
            return
        }
        viewModelScope.launch {
            authRepository.login(AuthData(form.portal, form.email, form.password))
                .collectLatest { result ->
                    if (result is DataResult.Failure){
                        _uiEvents.send(AuthUiEvent.ShowSnackbarError(
                            resourceManager.getErrorString(result.e))
                        )
                    }
                }
            switchIsLoading()
        }
    }

    private fun validateForm(form: AuthFormState): Boolean{
        val errors = mutableMapOf<Field, ValidationError>()
        validatePortal(form.portal)?.let { errors[Field.PORTAL] = it }
        validateEmail(form.email)?.let { errors[Field.EMAIL] = it }
        validatePassword(form.password)?.let { errors[Field.PASSWORD] = it }

        _uiState.update { it.copy(inputErrors = errors) }
        return errors.isEmpty()
    }

    private fun validatePortal(value: String): ValidationError? {
        val regex = """^(https://)?[a-zA-Z0-9-]+\.onlyoffice\.com/?${'$'}""".toRegex()
        return when{
            value.isBlank() -> ValidationError.Empty
            !regex.matches(value) -> ValidationError.InvalidPortalFormat
            else -> null
        }
    }

    private fun validateEmail(value: String): ValidationError?{
        return when{
            value.isBlank() -> ValidationError.Empty
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> ValidationError.InvalidEmailFormat
            else -> null
        }
    }

    private fun validatePassword(value: String): ValidationError?{
        return when{
            value.isBlank() -> ValidationError.Empty
            else -> null
        }
    }

    private fun switchIsLoading(){
        _uiState.update { it.copy(isLoading = !it.isLoading) }
    }

    //ONLY FOR DEMO
    fun useTestData(use: Boolean){
        if (use) _uiState.update { it.copy(
            portal = "https://testdocspaceportal.onlyoffice.com",
            email = "1one.test901@gmail.com",
            password = "Testpass123"
        ) }
        else _uiState.update {
            it.copy(portal = "", email = "", password = "")
        }
    }
}
