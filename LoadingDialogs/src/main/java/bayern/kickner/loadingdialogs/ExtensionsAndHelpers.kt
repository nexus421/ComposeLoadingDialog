package bayern.kickner.loadingdialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun <T> rememberIt(t: T) = remember { mutableStateOf(t) }