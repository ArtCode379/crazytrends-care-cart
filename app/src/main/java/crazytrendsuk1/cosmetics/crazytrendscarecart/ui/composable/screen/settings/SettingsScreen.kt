package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize().padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Spa, null, tint = MaterialTheme.colorScheme.primary)
            Text("About", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(start = 10.dp))
        }
        Card(shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Column(modifier = Modifier.padding(18.dp)) {
                SettingRow("Company", "CRAZYTRENDSUK-1 LTD")
                HorizontalDivider(modifier = Modifier.padding(vertical = 14.dp))
                SettingRow("App", "Crazytrends Care Cart")
                HorizontalDivider(modifier = Modifier.padding(vertical = 14.dp))
                SettingRow("Version", "1.0")
            }
        }
        Text("Support", style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(top = 28.dp, bottom = 12.dp))
        Text("Questions about a reservation or product? Visit our customer support website.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Button(
            onClick = { context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://crazytrends-uk.shop"))) },
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
        ) {
            Text("Customer Support")
            Icon(Icons.Default.OpenInNew, null, modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
private fun SettingRow(label: String, value: String) {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(label, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.titleMedium)
    }
}
