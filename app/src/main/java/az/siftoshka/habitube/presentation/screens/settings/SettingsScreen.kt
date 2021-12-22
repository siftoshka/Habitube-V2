package az.siftoshka.habitube.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import az.siftoshka.habitube.R
import az.siftoshka.habitube.domain.util.*
import az.siftoshka.habitube.presentation.screens.settings.fileds.*
import az.siftoshka.habitube.presentation.theme.HabitubeV2Theme
import az.siftoshka.habitube.presentation.util.Padding
import az.siftoshka.habitube.presentation.util.Screen

/**
 * Composable function of the Settings screen.
 */
@Composable
fun SettingsScreen(
    navController: NavController,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val adultState = remember { mutableStateOf(viewModel.isAdultVisible()) }

    HabitubeV2Theme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                Column(Modifier.padding(horizontal = Padding.Default)) {
                    SettingsTitleText(text = R.string.text_general)
                    SettingsButtonField(
                        text = R.string.text_language,
                        getCurrentLanguage()
                    ) { navController.navigate(Screen.LanguageScreen.route) }
                    SettingsButtonField(text = R.string.text_sort, secondary = stringResource(id = viewModel.getSortType())) {
                        navController.navigate(Screen.SortScreen.route)
                    }
                    SettingsSwitchField(
                        text = R.string.text_adult_title,
                        description = R.string.text_adult_description,
                        isChecked = adultState
                    ) {
                        adultState.value = it
                        viewModel.changeAdultVisibility()
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    SettingsTitleText(text = R.string.text_library)
                    SettingsButtonField(text = R.string.text_storage, secondary = "") {
                        navController.navigate(Screen.StorageScreen.route)
                    }
                    SettingsSimpleButtonField(R.string.text_clear_cache) {
                        context.clearCache()
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    SettingsTitleText(text = R.string.text_about)
                    SettingsTextField(text = R.string.text_app_version, secondaryText = R.string.version_name)
                    ContactMeField(text = R.string.text_contact_me) {
                        when (it) {
                            SocialNetworks.TELEGRAM -> context.getTelegramIntent()
                            SocialNetworks.GITHUB -> context.getGithubIntent()
                            SocialNetworks.INSTAGRAM -> context.getInstagramIntent()
                        }
                    }
                    SettingsButtonField(text = R.string.text_privacy, secondary = "") {
                        navController.navigate(Screen.WebScreen.route + "/privacy")
                    }
                    SettingsButtonField(text = R.string.text_terms, secondary = "") {
                        navController.navigate(Screen.WebScreen.route + "/terms")
                    }
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }
        }
    }
}