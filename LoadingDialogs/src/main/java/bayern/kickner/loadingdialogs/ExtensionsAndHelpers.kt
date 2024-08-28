package bayern.kickner.loadingdialogs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

/**
 * This is a shortcut for remember { mutableStateOf(t) }.
 * For Int, Double, Float on Android don't use this. There are optimized versions like mutableIntStateOf, etc.
 */
@Composable
fun <T> rememberIt(t: T) = remember { mutableStateOf(t) }
