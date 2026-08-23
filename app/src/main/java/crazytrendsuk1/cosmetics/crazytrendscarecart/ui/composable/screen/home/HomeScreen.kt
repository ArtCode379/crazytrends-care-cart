package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.model.Product
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.model.ProductCategory
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.shared.MZFCMContentWrapper
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.shared.MZFCMEmptyView
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.state.DataUiState
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.ProductViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    MZFCMContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductGallery(
                products = (state as DataUiState.Populated).data,
                modifier = modifier,
                onProductClick = onNavigateToProductDetails,
            )
        },
        dataEmpty = {
            MZFCMEmptyView(
                primaryText = stringResource(R.string.mzfcm_products_state_empty_primary_text),
                modifier = modifier.fillMaxSize(),
            )
        },
    )
}

@Composable
private fun ProductGallery(products: List<Product>, modifier: Modifier, onProductClick: (Int) -> Unit) {
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val shown = products.filter { category == null || it.category == category }

    Column(modifier = modifier.fillMaxSize()) {
        Card(
            onClick = { onProductClick(products.first().id) },
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
        ) {
            Box {
                AsyncImage(
                    model = products.first().imageUrl,
                    contentDescription = products.first().title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize(),
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Brush.verticalGradient(listOf(androidx.compose.ui.graphics.Color.Transparent, androidx.compose.ui.graphics.Color.Black.copy(alpha = 0.72f)))),
                )
                Column(modifier = Modifier.align(Alignment.BottomStart).padding(20.dp)) {
                    Text("THE DAILY EDIT", color = androidx.compose.ui.graphics.Color.White, style = MaterialTheme.typography.labelLarge)
                    Text(products.first().title, color = androidx.compose.ui.graphics.Color.White, style = MaterialTheme.typography.headlineMedium)
                    Text(stringResource(R.string.mzfcm_price, products.first().price), color = androidx.compose.ui.graphics.Color.White)
                }
            }
        }
        LazyRow(
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            item {
                FilterChip(selected = category == null, onClick = { category = null }, label = { Text("All") })
            }
            items(ProductCategory.entries.size) { index ->
                val item = ProductCategory.entries[index]
                FilterChip(
                    selected = category == item,
                    onClick = { category = item },
                    label = { Text(stringResource(item.titleRes)) },
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(shown, key = { it.id }) { product ->
                ProductCard(product, onProductClick)
            }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onProductClick: (Int) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(18.dp),
        modifier = Modifier.clickable { onProductClick(product.id) },
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (product.id % 2 == 0) 220.dp else 170.dp)
                .clip(RoundedCornerShape(topStart = 18.dp, topEnd = 18.dp)),
        )
        Column(modifier = Modifier.padding(12.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Text(stringResource(product.category.titleRes), color = MaterialTheme.colorScheme.onSurfaceVariant, style = MaterialTheme.typography.bodyMedium)
            Text(stringResource(R.string.mzfcm_price, product.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleMedium)
        }
    }
}
