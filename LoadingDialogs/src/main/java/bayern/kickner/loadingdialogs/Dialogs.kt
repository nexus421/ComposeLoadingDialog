package bayern.kickner.loadingdialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

/**
 * This is a simple Dialog you can use to easy show information through a dialog.
 *
 * Use it for example like:
 * var showInfoDialog: InfoDialogOption? by remember { mutableStateOf(null) }
 * showInfoDialog?.let { InfoDialog(it) }
 *
 * Set the option to display the dialog or set to null to hide it.
 *
 * @param option all the settings for this dialog
 */
@Composable
fun InfoDialog(option: InfoDialogOption) {
    Dialog(onDismissRequest = option.onDismiss) {
        Column(
            Modifier
                .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.large)
                .border(1.dp, MaterialTheme.colorScheme.outline, MaterialTheme.shapes.large)
                .padding(8.dp)) {
            option.title()

            HorizontalDivider(Modifier.padding(top = 4.dp, bottom = 4.dp))

            option.message()

            Spacer(modifier = Modifier.width(8.dp))

            Row {
                option.onNegative?.let {
                    OutlinedButton(onClick = { it.second() }) {
                        Text(it.first)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                option.onNeutral?.let {
                    OutlinedButton(onClick = { it.second() }) {
                        Text(it.first)
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                OutlinedButton(onClick = { option.onPositive.second() }) {
                    Text(option.onPositive.first)
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewInfoDialog() {
    InfoDialog(InfoDialogOption("Title", "Message", "Positive" to {}, "Negative" to {}, "Neutral" to {}))
}

/**
 * Use this class to configure the [InfoDialog]-Composable.
 *
 * @param title configure your own title with an composable or use the second constructor for default title. Required.
 * @param message configure your own message with an composable or use the second constructor for default message. Required.
 * @param onPositive configure this button with a String for the button text and a callback for the on button click. Required. Use it like "Test" to { ... }
 * @param onNegative configure this button with a String for the button text and a callback for the on button click. Defaults to null to hide the button. "Test" to { ... }
 * @param onNeutral configure this button with a String for the button text and a callback for the on button click. Defaults to null to hide the button. "Test" to { ... }
 * @param onDismiss callback for when the dialog is dismissed. Default is an empty function to avoid to hide the dialog on back press or click outside "Test" to { ... }
 */
data class InfoDialogOption(
    val title: @Composable () -> Unit,
    val message: @Composable () -> Unit,
    val onPositive: Pair<String, () -> Unit>,
    val onNegative: Pair<String, () -> Unit>? = null,
    val onNeutral: Pair<String, () -> Unit>? = null,
    val onDismiss: () -> Unit = {}
) {

    constructor(
        title: String,
        message: String,
        onPositive: Pair<String, () -> Unit>,
        onNegative: Pair<String, () -> Unit>? = null,
        onNeutral: Pair<String, () -> Unit>? = null,
        onDismiss: () -> Unit = {}
    ) : this(
        { Text(title, fontWeight = FontWeight.Bold) },
        { Text(message) },
        onPositive,
        onNegative,
        onNeutral,
        onDismiss
    )
}
