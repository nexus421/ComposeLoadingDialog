package bayern.kickner.loadingdialogs

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
 * @param show true if Dialog should be visible
 * @param msg Nachricht die dem Nutzer unter der Ladeanzeige angezeigt werden kann. - Message, visibile to user
 * @param type Art der Ladeanzeige. Type of LoadingDialog
 * @param progressColor Farbe der Ladeanzeige. LoadingDialog color
 */
@ExperimentalAnimationApi
@Composable
fun LoadingDialog(
    show: Boolean,
    msg: String = "",
    type: Type = Type.CIRCLE,
    progressColor: Color = MaterialTheme.colors.primary,
    onDismissRequest: (() -> Unit)? = null
) {
    if(show.not()) return

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