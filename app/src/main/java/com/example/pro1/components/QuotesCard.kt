package com.example.pro1.components

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pro1.models.Quotes

enum class QuotesMode {
    FULL {
        override fun modifier1(modifier: Modifier): Modifier = modifier.height(400.dp)
        override fun textAligner(): TextAlign = TextAlign.Center
        override val textLines: Int = 10
    },
    SMALL {
        override fun modifier1(modifier: Modifier): Modifier = modifier.wrapContentHeight()
        override fun textAligner(): TextAlign = TextAlign.Left
        override val textLines: Int = 3
    };

    abstract fun modifier1(modifier: Modifier): Modifier
    abstract fun textAligner(): TextAlign
    abstract val textLines: Int
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuotesCard(
    quote: Quotes,
    modifier: Modifier,
    modePre: QuotesMode = QuotesMode.SMALL,
    changer: () -> Unit = {},
) {
    var mode by remember {
        mutableStateOf(modePre)
    }
    val modifier1 = mode.modifier1(
        modifier
            .animateContentSize()
    )
    val modifierColumn1 = if (mode == QuotesMode.FULL) {
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(20.dp)
    } else {
        Modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 18.dp,
                end = 18.dp
            )
    }
    val textAligner = mode.textAligner()
    Card(
        modifier = modifier1
            .fillMaxWidth()
            .combinedClickable (
                onClick = {
                    mode = if (mode == QuotesMode.SMALL) QuotesMode.FULL
                    else QuotesMode.SMALL
                },
                onLongClick = changer
            )
        ,
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = modifierColumn1
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = quote.quote,
                style = typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = textAligner,
                fontWeight = FontWeight.Bold,
                maxLines = mode.textLines
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = quote.author,
                style = typography.labelSmall,
                textAlign = textAligner,
                color = MaterialTheme.colorScheme.onBackground,
                fontFamily = FontFamily.Cursive
            )

        }
    }
}

@Preview
@Composable
fun PreviewQuotesCard1() {
    QuotesCard(
        quote = Quotes(
            "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment. ",
            "Hitler"
        ),
        Modifier,
//        QuotesMode.FULL
    )
}

//@Preview
//@Composable
//fun PreviewQuotesCard2() {
//    QuotesCard(
//        quote = Quotes(
//            "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.  ",
//            "Hitler"
//        ),
//        Modifier,
////        QuotesMode.SMALL
//    )
//}