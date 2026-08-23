package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.data.entity.OrderEntity
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.shared.MZFCMContentWrapper
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.state.DataUiState
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.theme.Success
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.OrderViewModel
import java.time.format.DateTimeFormatter
import org.koin.androidx.compose.koinViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    MZFCMContentWrapper(
        dataState = state,
        dataPopulated = {
            val orders = (state as DataUiState.Populated).data.sortedByDescending { it.timestamp }
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = modifier.fillMaxSize().padding(16.dp)) {
                items(orders, key = { it.orderNumber }) { OrderCard(it) }
            }
        },
        dataEmpty = {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = modifier.fillMaxSize()) {
                Icon(Icons.Default.ReceiptLong, null, tint = MaterialTheme.colorScheme.primary)
                Text(stringResource(R.string.mzfcm_orders_state_empty_primary_text), style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(top = 12.dp))
            }
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(shape = RoundedCornerShape(18.dp)) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Text(stringResource(R.string.mzfcm_order_number, order.orderNumber), style = MaterialTheme.typography.titleMedium)
                Surface(color = Success.copy(alpha = 0.12f), shape = RoundedCornerShape(50)) {
                    Text("Reserved", color = Success, modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp))
                }
            }
            Text(order.timestamp.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")), color = MaterialTheme.colorScheme.onSurfaceVariant)
            Text(order.description, modifier = Modifier.padding(vertical = 12.dp))
            Text(stringResource(R.string.mzfcm_price, order.price), color = MaterialTheme.colorScheme.primary, style = MaterialTheme.typography.titleLarge)
            Text("Ready for collection for 24 hours", color = MaterialTheme.colorScheme.secondary, style = MaterialTheme.typography.labelLarge)
        }
    }
}
