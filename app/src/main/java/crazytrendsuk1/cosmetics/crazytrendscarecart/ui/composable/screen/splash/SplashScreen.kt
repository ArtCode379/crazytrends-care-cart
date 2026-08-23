package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.theme.Rose
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.theme.RoseDark
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.MZFCMSplashVM
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: MZFCMSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    val reveal = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        reveal.animateTo(1f, tween(800))
        delay(700)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Image(
            painter = painterResource(R.drawable.onboarding_1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.55f),
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .background(Brush.verticalGradient(listOf(Rose, RoseDark))),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .alpha(reveal.value)
                    .scale(0.8f + reveal.value * 0.2f)
                    .padding(24.dp),
            ) {
                Image(
                    painter = painterResource(R.drawable.mzfcm_ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier
                        .size(84.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(24.dp))
                        .padding(12.dp),
                )
                Text(
                    text = stringResource(R.string.mzfcm_app_name),
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(top = 18.dp),
                )
            }
        }
    }
}
