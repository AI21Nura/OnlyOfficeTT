package com.ainsln.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ainsln.core.model.profile.UserProfile
import com.ainsln.core.ui.component.AppBar
import com.ainsln.core.ui.component.RenderUiState
import com.ainsln.core.ui.state.UiState

@Composable
internal fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel()
){
    val uiState by viewModel.uiState.collectAsState()
    ProfileScreenContent(
        uiState = uiState,
        onLogoutClick = viewModel::logout,
        errorAction = viewModel::loadProfile
    )
}

@Composable
private fun ProfileScreenContent(
    uiState: UiState<UserProfile>,
    onLogoutClick: () -> Unit,
    errorAction: () -> Unit
){
    Scaffold(
        topBar = {
            AppBar(title = stringResource(R.string.profile))
        }
    ) { innerPadding ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)){
            RenderUiState(
                uiState = uiState,
                crossfadeLabel = "ProfileScreen",
                successContent = { profile -> ProfileBlock(profile, onLogoutClick) },
                errorAction = errorAction
            )
        }
    }
}

@Composable
private fun ProfileBlock(
    profile: UserProfile,
    onLogoutClick: () -> Unit
){
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        ProfileAvatar(profile.avatarOriginalUrl)

        Text(
            text = "${profile.firstName} ${profile.lastName}",
            style = MaterialTheme.typography.titleLarge
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = profile.email,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Button(
            onClick = onLogoutClick,
            modifier = Modifier
                .width(144.dp)
                .padding(top = 24.dp)
        ) {
            Text(stringResource(R.string.logout))
        }
    }
}

@Composable
private fun ProfileAvatar(url: String){
    SubcomposeAsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        loading = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(Modifier.size(32.dp))
            }
        },
        error = {
            Icon(
                painter = painterResource(R.drawable.ic_avatar),
                contentDescription = null
            )
        },
        contentDescription = stringResource(R.string.user_avatar),
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(164.dp)
            .clip(CircleShape)
    )
}
