package com.yongjincompany.dsmtcg.ui.splash

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.yongjincompany.dsmtcg.R
import com.yongjincompany.dsmtcg.ui.MainActivity
import com.yongjincompany.dsmtcg.ui.StartActivity
import com.yongjincompany.dsmtcg.viewmodel.splash.SplashViewModel
import kotlinx.coroutines.launch

@Composable
fun Splash() {
    val viewModel: SplashViewModel = hiltViewModel()
    viewModel.autoLogin()
    Splash(viewModel)
}


@Composable
fun Splash(
    viewModel: SplashViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            viewModel.eventFlow.collect {
                when (it) {
                    SplashViewModel.Event.AutoLoginSuccess -> startMainActivity(context)
                    SplashViewModel.Event.NeedLogin -> startHomeActivity(context)
                }
            }
        }
    }
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val image = rememberImagePainter(
            request = ImageRequest.Builder(context)
                .data(R.drawable.logo2)
                .build()
        )
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                modifier = Modifier.size(180.dp),
                painter = image,
                contentDescription = null
            )
            Column {
                Spacer(Modifier.size(180.dp))
                Title2(
                    text = "DSM-TCG",
                    color = Color.Black,

                )
            }
        }
    }
}

fun startHomeActivity(context: Context) {
    val intent = Intent(context, StartActivity::class.java)
    context.startActivity(intent)
}

fun startMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    context.startActivity(intent)
}

@Preview(showBackground = true)
@Composable
fun PreviewSplash() {
    Splash()
}

@Composable
fun Title2(
    text: String,
    modifier: Modifier = Modifier,
    lineHeight: Int = 54,
    letterSpacing: Int = 0,
    color: Color = Color.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {}
) {
    Typography(
        text = text,
        modifier = modifier,
        weight = FontWeight.W500,
        size = 36,
        color = color,
        lineHeight = lineHeight,
        letterSpacing = letterSpacing,
        baselineToTop = 38,
        baselineToBottom = 16,
        textDecoration = textDecoration,
        textAlign = textAlign,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun Typography(
    text: String,
    modifier: Modifier = Modifier,
    weight: FontWeight,
    size: Int,
    color: Color,
    lineHeight: Int,
    letterSpacing: Int,
    baselineToTop: Int,
    baselineToBottom: Int,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign?,
    overflow: TextOverflow,
    softWrap: Boolean,
    maxLines: Int,
    onTextLayout: (TextLayoutResult) -> Unit
) {

    val baselineModifier = modifier.paddingFromBaseline(top = baselineToTop.sp, bottom = baselineToBottom.sp)
    Text(
        style = TextStyle(
            color = color,
            lineHeight = lineHeight.sp,
            letterSpacing = letterSpacing.sp,
            fontSize = size.sp,
            fontFamily = notoSansFamily,
            fontWeight = weight,
            textDecoration = textDecoration,
            textAlign = textAlign,
        ),
        text = text,
        modifier = baselineModifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout
    )
}

val notoSansFamily = FontFamily(
    Font(R.font.nanumsquare_aceb, FontWeight.Black),
    Font(R.font.nanumsquare_aceb, FontWeight.Bold),
    Font(R.font.nanumsquare_aceb, FontWeight.Medium),
    Font(R.font.nanumsquare_aceb, FontWeight.Normal),
    Font(R.font.nanumsquare_aceb, FontWeight.Light),
    Font(R.font.nanumsquare_aceb, FontWeight.Thin)
)