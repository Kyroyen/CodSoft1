package com.example.pro1.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pro1.R
import com.example.pro1.components.QuotesCard
import com.example.pro1.components.QuotesMode
import com.example.pro1.components.TopBar
import com.example.pro1.data.samplequotes
import com.example.pro1.models.Quotes
import kotlin.text.Typography.quote

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuotesListScreen(
    navigateToDetailed: (Quotes) -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(title = stringResource(id = R.string.text_jetquotes))
        }
    ) { paddingVal ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingVal)
        ){
            items(
                count = samplequotes.size,
                key = {index -> samplequotes[index].author},
                itemContent = {
                    val quote = samplequotes[it]
                    QuotesCard(
                        quote = quote,
                        modifier = Modifier
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = 10.dp,
                                bottom = 10.dp
                            ),
                        changer = { navigateToDetailed(quote) }
                    )
                }
            )
        }
    }
}