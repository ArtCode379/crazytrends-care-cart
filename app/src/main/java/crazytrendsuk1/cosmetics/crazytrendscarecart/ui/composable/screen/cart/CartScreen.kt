package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.RemoveShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.shared.MZFCMContentWrapper
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.state.CartItemUiState
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.state.DataUiState
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.CartViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    MZFCMContentWrapper(
        dataState = state,
        dataPopulated = {
            CartList(
                items = (state as DataUiState.Populated).data,
                total = total,
                modifier = modifier,
                plus = viewModel::incrementProductInCart,
                minus = viewModel::decrementItemInCart,
                remove = viewModel::deleteFromCart,
                checkout = onNavigateToCheckoutScreen,
            )
        },
        dataEmpty = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize().padding(32.dp),
            ) {
                Icon(Icons.Default.RemoveShoppingCart, null, tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(72.dp))
                Text("Your ritual starts here", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = 20.dp))
                Text(stringResource(R.string.mzfcm_cart_state_empty_primary_text), color = MaterialTheme.colorScheme.onSurfaceVariant)
                OutlinedButton(onClick = { onNavigateToCheckoutScreen() }, modifier = Modifier.padding(top = 18.dp)) { Text("Start Shopping") }
            }
        },
    )
}

@Composable
private fun CartList(
    items: List<CartItemUiState>,
    total: Double,
    modifier: Modifier,
    plus: (Int) -> Unit,
    minus: (Int) -> Unit,
    remove: (Int) -> Unit,
    checkout: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.weight(1f),
        ) {
            items(items, key = { it.productId }) { item ->
                Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.padding(horizontal = 16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
                        AsyncImage(item.productImageUrl, item.productTitle, contentScale = ContentScale.Crop, modifier = Modifier.size(72.dp))
                        Column(modifier = Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text(stringResource(R.string.mzfcm_price, item.productPrice), color = MaterialTheme.colorScheme.primary)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(onClick = { if (item.quantity == 1) remove(item.productId) else minus(item.productId) }) { Text("−") }
                                Text(item.quantity.toString())
                                IconButton(onClick = { plus(item.productId) }) { Text("+") }
                            }
                        }
                        IconButton(onClick = { remove(item.productId) }) { Icon(Icons.Default.DeleteOutline, stringResource(R.string.mzfcm_delete_item_icon_description)) }
                    }
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth().padding(20.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Subtotal")
                Text(stringResource(R.string.mzfcm_price, total))
            }
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Text("Total", style = MaterialTheme.typography.titleLarge)
                Text(stringResource(R.string.mzfcm_price, total), style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.primary)
            }
            Button(onClick = checkout, modifier = Modifier.fillMaxWidth()) { Text("Proceed to Checkout") }
        }
    }
}
