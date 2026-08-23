package crazytrendsuk1.cosmetics.crazytrendscarecart.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import crazytrendsuk1.cosmetics.crazytrendscarecart.R
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.state.DataUiState
import crazytrendsuk1.cosmetics.crazytrendscarecart.ui.viewmodel.CheckoutViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val orderState by viewModel.orderState.collectAsStateWithLifecycle()
    val emailInvalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    val enabled by remember {
        derivedStateOf {
            viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank()
        }
    }
    if (orderState is DataUiState.Populated) {
        CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    }
    CheckoutContent(
        customerFirstName = viewModel.customerFirstName,
        customerLastName = viewModel.customerLastName,
        customerEmail = viewModel.customerEmail,
        isEmailInvalid = emailInvalid,
        modifier = modifier,
        focusManager = focusManager,
        isButtonEnabled = enabled,
        onFirstNameChanged = viewModel::updateCustomerFirstName,
        onLastNameChanged = viewModel::updateCustomerLastName,
        onEmailChanged = viewModel::updateCustomerEmail,
        onPlaceOrderButtonClick = viewModel::placeOrder,
    )
}

@Composable
private fun CheckoutContent(
    customerFirstName: String,
    customerLastName: String,
    customerEmail: String,
    isEmailInvalid: Boolean,
    modifier: Modifier,
    focusManager: FocusManager,
    isButtonEnabled: Boolean,
    onFirstNameChanged: (String) -> Unit,
    onLastNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPlaceOrderButtonClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
    ) {
        Text("Reserve your collection", style = MaterialTheme.typography.headlineMedium)
        Text("Tell us who will collect the order. We will hold it in store for 24 hours after confirmation.", color = MaterialTheme.colorScheme.onSurfaceVariant)
        Card(shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth().padding(top = 20.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                CheckoutTextField(customerFirstName, onFirstNameChanged, stringResource(R.string.mzfcm_checkout_text_field_first_name), Modifier.fillMaxWidth())
                CheckoutTextField(customerLastName, onLastNameChanged, stringResource(R.string.mzfcm_checkout_text_field_last_name), Modifier.fillMaxWidth().padding(top = 12.dp))
                CheckoutTextField(
                    input = customerEmail,
                    onInputChange = onEmailChanged,
                    labelText = stringResource(R.string.mzfcm_checkout_text_field_email),
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    isError = isEmailInvalid,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                )
                if (isEmailInvalid) {
                    Text("Enter a valid email address", color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
        Card(shape = RoundedCornerShape(18.dp), modifier = Modifier.fillMaxWidth().padding(top = 16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Collection promise", style = MaterialTheme.typography.titleLarge)
                Text("Your confirmation includes a unique order number. Bring it to the store within 24 hours to collect your reserved products.", modifier = Modifier.padding(top = 8.dp))
            }
        }
        Button(
            onClick = onPlaceOrderButtonClick,
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
        ) {
            Text(stringResource(R.string.mzfcm_button_confirm_order_label))
        }
    }
}

@Composable
fun CheckoutTextField(
    input: String,
    onInputChange: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
) {
    OutlinedTextField(
        value = input,
        onValueChange = onInputChange,
        modifier = modifier,
        enabled = enabled,
        label = { Text(labelText) },
        isError = isError,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        ),
    )
}
