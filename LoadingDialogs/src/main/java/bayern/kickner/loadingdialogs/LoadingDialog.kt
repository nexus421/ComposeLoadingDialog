package bayern.kickner.loadingdialogs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

enum class Type {
    CIRCLE, LINE, KITT
}

/**
 * Dialog der einen Ladebalken oder Ladekreis anzeigt, wenn zB eine Aktion im Hintergrund ausgeführt wird.
 * Die Ladeanzeige bewegt sich kontinuierlich.
 *
 * This is a ready to use Loading Dialog.
 * You are able to choose between three loading types --> Circle, Line or Kitt!
 *
 * @param msg Nachricht die dem Nutzer unter der Ladeanzeige angezeigt werden kann. - Message, visibile to user
 * @param type Art der Ladeanzeige. Type of LoadingDialog
 * @param progressColor Farbe der Ladeanzeige. LoadingDialog color
 * @param onDismissRequest Will be called if the user presses on empty space or on the back button
 * @param contentBelowLoading Content below the loading animation. Default is a Text
 */
@Composable
fun LoadingDialog(
    msg: String = "",
    type: Type = Type.KITT,
    progressColor: Color = MaterialTheme.colors.primary,
    onDismissRequest: (() -> Unit)? = null,
    contentBelowLoading: @Composable () -> Unit = {
        Text(
            text = msg,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
) {
    Dialog(onDismissRequest = {
        onDismissRequest?.let { it() }
    }) {
        Column {
            when (type) {
                Type.CIRCLE -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = progressColor
                )
                Type.LINE -> LinearProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = progressColor
                )
                else -> {
                    KittLoadingLightAnimated()
                }
            }

            contentBelowLoading()
        }
    }
}

/**
 * Dialog der einen Ladebalken oder Ladekreis anzeigt, wenn zB eine Aktion im Hintergrund ausgeführt wird.
 * Die Bewegung der Ladeanzeige wird duch "progress" definiert. Bedeutet: Die Ladeanzeige bewegt sich
 * zu der Position von "progress" im Rahmen von 0 bis 1.
 * 0.1f -> 10% sind geschafft.
 * 0.5f -> 50% sind geschafft.
 *
 * LoadingDialog with a progress.
 *
 * @param progress Fortschritsanzeige im Bereich von 0f bis 1f
 * @param msg Nachricht die dem Nutzer unter der Ladeanzeige angezeigt werden kann.
 * @param circle true == Runde Ladeanzeige, false == Ladebalken
 * @param progressColor Farbe der Ladeanzeige
 */
@Composable
fun LoadingDialogWithProgress(
    progress: Float, msg: String = "", circle: Boolean = true,
    progressColor: Color = MaterialTheme.colors.primary
) {
    Dialog(onDismissRequest = { }) {
        Column {
            if (circle) CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = progressColor
            )
            else LinearProgressIndicator(
                progress = progress,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = progressColor
            )
            Text(
                text = msg,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}