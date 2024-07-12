package bayern.kickner.composeloadingdialog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import bayern.kickner.composeloadingdialog.ui.theme.ComposeLoadingDialogTheme
import bayern.kickner.loadingdialogs.InfoDialog
import bayern.kickner.loadingdialogs.InfoDialogOption
import bayern.kickner.loadingdialogs.LoadingDialog
import bayern.kickner.loadingdialogs.Type
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var progress by mutableFloatStateOf(0.1f)

        setContent {
            ComposeLoadingDialogTheme {
                Scaffold {
                    Box(modifier = Modifier.padding(it)) {

                        // A surface container using the 'background' color from the theme
                        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                            LoadingDialog("Loading some stuff...", type = Type.KITT)
                            //LoadingDialogWithProgress(progress = progress)

                        }
//                        InfoDialog(InfoDialogOption("Bananarama", "sadfasdöifjöasdf", "Test" to {}))
                    }
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            while (progress < 1) {
                delay(100)
                progress += 0.01f
            }
        }
    }
}