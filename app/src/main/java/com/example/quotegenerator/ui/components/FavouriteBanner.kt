package com.example.quotegenerator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quotegenerator.R
import com.example.quotegenerator.ui.theme.QuoteGeneratorTheme
import com.example.quotegenerator.ui.theme.secondaryContainer
import com.example.quotegenerator.ui.theme.tertiaryContainer

@Composable
fun FavouriteBanner(
    title: String,
    favouriteCount: Int,
    onClick:() -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(85.dp)
    ) {
        Box(
            modifier = Modifier
                .width(377.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(topStart = 6.dp, topEnd = 6.dp))
                .background(secondaryContainer)
                .align(Alignment.BottomStart)
                .clickable { onClick() }
        ) {
            Text(
                text = title,
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(tertiaryContainer)
                .align(Alignment.TopEnd)
        )
        {
            Text(
                text = favouriteCount.toString(),
                modifier = Modifier.align(Alignment.Center),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }

}

@Preview
@Composable
fun TitleBannerPreview() {
    QuoteGeneratorTheme {
        FavouriteBanner(title = stringResource(R.string.click_here_to_view_favourite_quotes), favouriteCount = 0, onClick = {})
    }
}