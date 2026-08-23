package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.model.Product
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.shared.MZFCMContentWrapper
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.shared.MZFCMEmptyView
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.state.DataUiState
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.ProductDetailsViewModel
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    MZFCMContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductDetail((state as DataUiState.Populated).data, modifier, viewModel::addProductToCart)
        },
        dataEmpty = {
            MZFCMEmptyView(
                primaryText = stringResource(R.string.mzfcm_product_details_state_empty_primary_text),
                modifier = modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun ProductDetail(product: Product, modifier: Modifier, addToCart: () -> Unit) {
    var cartAdded by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState(pageCount = { 3 })
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 92.dp),
        ) {
            HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth().height(330.dp)) {
                AsyncImage(model = product.imageUrl, contentDescription = product.title, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
            ) {
                repeat(3) { index ->
                    Box(
                        modifier = Modifier
                            .padding(3.dp)
                            .width(if (pagerState.currentPage == index) 20.dp else 7.dp)
                            .height(7.dp)
                            .background(if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline, CircleShape),
                    )
                }
            }
            Column(modifier = Modifier.padding(20.dp)) {
                Text(product.title, style = MaterialTheme.typography.headlineMedium)
                Text(stringResource(product.category.titleRes).uppercase(), color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
                Text(product.description, color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 18.dp))
                Text("Why you'll love it", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 24.dp))
                Text("Thoughtfully selected ingredients, a sensorial texture and a simple place in your everyday ritual.", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp),
        ) {
            Text(stringResource(R.string.mzfcm_price, product.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
            Button(
                onClick = {
                    addToCart()
                    cartAdded = true
                },
                modifier = Modifier.weight(1f),
            ) { Text(stringResource(R.string.mzfcm_button_add_to_cart_label)) }
        }
        AnimatedVisibility(
            visible = cartAdded,
            enter = slideInVertically { it },
            exit = fadeOut(),
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 76.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface).padding(16.dp),
            ) {
                Icon(Icons.Default.Check, null, tint = MaterialTheme.colorScheme.primary)
                Text("Added to cart", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
