package com.example.pro1.views

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pro1.components.CopyButton
import com.example.pro1.components.QuotesCard
import com.example.pro1.components.QuotesMode
import com.example.pro1.components.ShareButton
import com.example.pro1.models.Quotes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedQuotes(
    quote: Quotes
) {
    val context = LocalContext.current
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(paddingValues),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            QuotesCard(
                quote = quote,
                modifier = Modifier
                    .padding(20.dp),
                modePre = QuotesMode.FULL
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ShareButton(
                    action = {
//                        Log.d("Bhosda","${quote.quote} ~ ${quote.author}")
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "${quote.quote} ~ ${quote.author}")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
                )
                CopyButton(
                    action = {
                        val clipboardManager =
                            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                        val clip = ClipData.newPlainText("Quote", "${quote.quote} ~ ${quote.author}")
                        clipboardManager.setPrimaryClip(clip)
                    }
                )

            }
        }
    }
}

@Preview
@Composable
fun PreviewDetailedQuotes() {
    DetailedQuotes(
        quote = Quotes(
            "To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment. ",
            "Hitler"
        ),
    )
}