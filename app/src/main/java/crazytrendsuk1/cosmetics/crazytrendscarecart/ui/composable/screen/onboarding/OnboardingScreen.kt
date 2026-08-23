package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.MZFCMOnboardingVM
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int
)

private val onboardingPagesContent = listOf<OnboardingContent>(
    OnboardingContent(
        titleRes = R.string.mzfcm_page_1_title,
        descriptionRes = R.string.mzfcm_page_1_description,
        imageRes = R.drawable.mzfcm_ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.mzfcm_page_2_title,
        descriptionRes = R.string.mzfcm_page_2_description,
        imageRes = R.drawable.mzfcm_ic_launcher_background,
    ),
    OnboardingContent(
        titleRes = R.string.mzfcm_page_2_title,
        descriptionRes = R.string.mzfcm_page_3_description,
        imageRes = R.drawable.mzfcm_ic_launcher_background,
    ),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: MZFCMOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val onboardingSetState by viewModel.onboardingSetState.collectAsState()

    LaunchedEffect(onboardingSetState) {
        if (onboardingSetState) {
            onNavigateToHomeScreen()
        }
    }


}