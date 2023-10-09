package com.example.quotegenerator.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quotegenerator.R
import com.example.quotegenerator.ui.FavouriteViewModel
import com.example.quotegenerator.ui.theme.QuoteGeneratorTheme
import com.example.quotegenerator.ui.theme.primary
import com.example.quotegenerator.ui.theme.primaryContainer

@Composable
fun QuoteCard(
    viewModel: FavouriteViewModel,
    cropTop: Boolean = true,
    onClickFavourite: () -> Unit,
    onClickGenerate: () -> Unit,
) {
    val quote = viewModel.quote.value
    Card(
        colors = CardDefaults.cardColors(
            containerColor = primaryContainer
        ),
        shape = if (!cropTop) RoundedCornerShape(
            bottomStart = 6.dp,
            bottomEnd = 6.dp
        ) else RoundedCornerShape(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp)
        ) {
            Text(
                text = "“${quote.quote}”",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .align(alignment = Alignment.Start)
            )
            Text(
                text = quote.author,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier
                    .align(alignment = Alignment.End)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = onClickGenerate,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = primary
                    ),
                    shape = RoundedCornerShape(bottomStart = 6.dp),
                ) {
                    Text(
                        text = stringResource(R.string.generate_another_quote),
                        style = MaterialTheme.typography.bodyMedium,
                        color = primaryContainer
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                OutlinedButton(
                    onClick = onClickFavourite,
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        width = 2.dp,
                        brush = SolidColor(primary)
                    ),
                    shape = RoundedCornerShape(bottomEnd = 6.dp),
                ) {
                    Icon(
                        painterResource(id = if (quote.isFavourite) R.drawable.favorite_fill else R.drawable.favorite),
                        contentDescription = "Favourite",
                        tint = primary
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuoteCardPreview() {
    QuoteGeneratorTheme {
        QuoteCard(
            viewModel = FavouriteViewModel(),
            onClickFavourite = {},
            onClickGenerate = {}
        )
    }
}