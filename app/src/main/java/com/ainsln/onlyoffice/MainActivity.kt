package com.ainsln.onlyoffice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ainsln.core.ui.theme.OnlyOfficeTheme
import com.ainsln.onlyoffice.ui.AppContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OnlyOfficeTheme {
                val viewModel: MainActivityViewModel = hiltViewModel()
                val isAuthorizedState by viewModel.isAuthorized.collectAsState()
                AppContent(isAuthorizedState)
            }
        }
    }
}


