package com.example.pro1.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pro1.R

@Composable
fun Button(icon: Painter, name: String?, modifier: Modifier){
    Row(
       modifier = modifier
           .background(MaterialTheme.colorScheme.inverseOnSurface)
           .padding(12.dp),
    ){
        Icon(
            painter = icon,
            contentDescription = stringResource(id = R.string.text_cta_icons)
        )
        Spacer(
            modifier = Modifier
                .width(8.dp)
        )
        Text(
            text = name.toString(),
            maxLines = 1,
            style = typography.bodyMedium,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview
@Composable
fun ShareButton(
    modifier: Modifier = Modifier,
    action: () -> Unit = {},
){
    Button(
        icon = painterResource(id = R.drawable.ic_share),
        name = stringResource(id = R.string.text_share),
        modifier = modifier
            .clickable { action() },
    )
}

@Preview
@Composable
fun CopyButton(
    modifier: Modifier = Modifier,
    action: () -> Unit = {},
){
    Button(
        icon = painterResource(id = R.drawable.ic_copy),
        name = stringResource(id = R.string.text_copy),
        modifier = modifier
            .clickable {
//                Log.d("Bhosda","FUCK")
                action()
               },
    )
}

