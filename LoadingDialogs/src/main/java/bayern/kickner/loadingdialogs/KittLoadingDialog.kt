package bayern.kickner.loadingdialogs

import androidx.annotation.IntRange
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

private const val Start = -0.2f
private const val End = 1.2f

/**
 * You should use [KittLoadingLightAnimated].
 * But this will work without experimental features.
 */
@Composable
fun KittLoadingLight(
    colorOn: Color = Color(0xFFE30000),
    colorOnMiddle: Color = Color(0xFFDA2727),
    colorOnLight: Color = Color(0xFFDC5151),
    colorOff: Color = Color(0xFFE89A9A),
    segmentCount: Int = 9,
    segmentSpace: Dp = 3.dp,
    duration: Int = 2500
) {

    var part by remember { mutableStateOf(0) }
    var runForwards by remember { mutableStateOf(true) }

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        if (runForwards) Start else End,
        if (runForwards) End else Start,
        infiniteRepeatable(
            animation = keyframes {
                durationMillis = duration
                (if (runForwards) Start else End) at 0 with CubicBezierEasing(0.2f, 0f, 0.8f, 1f)
                (if (runForwards) End else Start) at duration
            }
        )
    )

    part = (progress * 10).roundToInt()

    if (part == segmentCount + 2) runForwards = false
    if (part == -2) runForwards = true

    Row(
        modifier = Modifier
            .background(Color.Black)
            .padding(top = segmentSpace, bottom = segmentSpace)
    ) {
        Spacer(modifier = Modifier.width(segmentSpace))
        repeat(segmentCount) {
            val first = it
            val second = if (runForwards) it + 1 else it - 1
            val third = if (runForwards) it + 2 else it - 2

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(if (first == part) colorOn else if (second == part) colorOnMiddle else if (third == part) colorOnLight else colorOff)
                    .height(10.dp)
            ) {
            }
            Spacer(modifier = Modifier.width(segmentSpace))
        }
    }
}

private const val START_NEW_LIGHT = 0f
private const val END_NEW_LIGHT = 1f

/**
 * Dynamisches Lauflicht aus Knigh Rider mit der Animation API.
 * Dynamic Light from Knight Rider. Build with the Animation API.
 * Prefered Way!
 *
 * Better use the simple pre-defined [LoadingDialog]
 *
 * This is the raw-method. Use LoadingDialog for prepaired and ready to use Dialog!
 *
 * @param segments Anzahl der einzelnen Segmente im Lauflicht
 * @param duration Zeit die das Lauflicht für eine Richtung brauchen soll (Millisekunden)
 * @param colorSegment Farbe eines Segments
 * @param backgroundColorSegment Hintergrundfarbe eines Segments. Diese ist sichtbar, wenn das Segment nicht aktiv leuchtet
 * @param segmentSpace Abstand zwischen den einzelnen Segmenten
 * @param backgroundColor Hintergrundfarbe des gesamten Lauflichtes
 * @param height Höhe des gesamten Lauflichtes
 * @param padding Padding um das gesamte Lauflicht
 * @param durationIn Zeit, bis das Segment die volle Farbstärke erreicht hat (Einblenden) (Alpha = 1)
 * @param durationOut Zeit, bis das Segment komplett ausgeblendet wird (Ausblenden) (Alpha = 0)
 */

@Composable
fun KittLoadingLightAnimated(
    @IntRange(from = 3, to = 30) segments: Int = 8,
    @IntRange(from = 250, to = Int.MAX_VALUE.toLong()) duration: Int = 1000,
    colorSegment: Color = Color(0xFFFF0000),
    backgroundColorSegment: Color = Color(0xC9940D0D),
    segmentSpace: Dp = 2.dp,
    backgroundColor: Color = Color.Black,
    height: Dp = 15.dp,
    padding: Dp = segmentSpace,
    durationIn: DurationIn = DurationIn.NORMAL,
    durationOut: DurationOut = DurationOut.NORMAL
) {
    check(segments in 3..30)
    check(duration in 250..Int.MAX_VALUE)

    val infiniteTransition = rememberInfiniteTransition()
    val progress by infiniteTransition.animateFloat(
        START_NEW_LIGHT,
        segments.toFloat(),
        infiniteRepeatable(
            repeatMode = RepeatMode.Reverse,
            animation = keyframes {
                durationMillis = duration
            }
        )
    )

    Row(
        modifier = Modifier
            .height(height)
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(padding)
    ) {
        Spacer(modifier = Modifier.width(segmentSpace))
        repeat(segments) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                SingleLightElement(
                    //Prüft, ob der aktuelle progress im Bereich des Segments liegt
                    visible = (progress in (it).toFloat()..(it + 1).toFloat()),//segmentsTimeRange[it]?.isInRange(progress) ?: false,
                    color = colorSegment,
                    backgroundColor = backgroundColorSegment,
                    durationMillisIn = duration / durationIn.num,
                    durationMillisOut = duration / durationOut.num
                )
            }
            Spacer(modifier = Modifier.width(segmentSpace))
        }
    }
}

@Composable
private fun SingleLightElement(
    visible: Boolean,
    color: Color = Color(0xFFFF0000),
    backgroundColor: Color = Color(0xC9940D0D),
    durationMillisIn: Int,
    durationMillisOut: Int
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(
                animationSpec = tween(
                    durationMillis = durationMillisIn,
                    easing = LinearEasing
                )
            ),
            exit = fadeOut(
                animationSpec = tween(
                    durationMillis = durationMillisOut,
                    easing = LinearEasing
                )
            )
        ) {
            Box(
                modifier = Modifier
                    .background(color)
                    .fillMaxSize()
            ) {
            }
        }
    }
}

private data class Range(val from: Double, val to: Double) {
    fun isInRange(num: Float): Boolean {
        return num in from..to
    }
}

enum class DurationIn(val num: Int) {
    FAST(400),
    NORMAL(200),
    SLOW(100),
    VERY_SLOW(50)
}

enum class DurationOut(val num: Int) {
    VERY_SLOW(1),
    SLOW(2),
    NORMAL(4),
    FAST(6),
    SUPER_FAST(8)
}