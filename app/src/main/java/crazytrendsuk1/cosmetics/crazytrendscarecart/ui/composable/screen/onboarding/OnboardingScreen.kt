package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.MZFCMOnboardingVM
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

data class OnboardingContent(
    @field:StringRes val titleRes: Int,
    @field:StringRes val descriptionRes: Int,
    @field:DrawableRes val imageRes: Int,
)

private val pages = listOf(
    OnboardingContent(R.string.mzfcm_page_1_title, R.string.mzfcm_page_1_description, R.drawable.onboarding_1),
    OnboardingContent(R.string.mzfcm_page_2_title, R.string.mzfcm_page_2_description, R.drawable.onboarding_2),
    OnboardingContent(R.string.mzfcm_page_3_title, R.string.mzfcm_page_3_description, R.drawable.onboarding_3),
)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: MZFCMOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val saved by viewModel.onboardingSetState.collectAsState()
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    LaunchedEffect(saved) {
        if (saved) onNavigateToHomeScreen()
    }

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(state = pagerState, modifier = Modifier.fillMaxSize()) { index ->
            val page = pages[index]
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    painter = painterResource(page.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.92f))
                        .padding(horizontal = 28.dp, vertical = 40.dp),
                ) {
                    Text(text = stringResource(page.titleRes), style = MaterialTheme.typography.displaySmall)
                    Text(
                        text = stringResource(page.descriptionRes),
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = 12.dp),
                    )
                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.94f))
                .padding(24.dp),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                pages.indices.forEach { index ->
                    Box(
                        modifier = Modifier
                            .size(if (index == pagerState.currentPage) 10.dp else 7.dp)
                            .background(
                                if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                CircleShape,
                            ),
                    )
                }
            }
            Button(
                onClick = {
                    if (pagerState.currentPage == pages.lastIndex) {
                        viewModel.setOnboarded()
                    } else {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 18.dp),
            ) {
                Text(if (pagerState.currentPage == pages.lastIndex) stringResource(R.string.mzfcm_start_button_title) else stringResource(R.string.mzfcm_next_button_title))
            }
        }
    }
}
