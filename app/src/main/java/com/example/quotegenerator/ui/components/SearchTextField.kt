package com.example.quotegenerator.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.quotegenerator.R
import com.example.quotegenerator.ui.theme.QuoteGeneratorTheme
import com.example.quotegenerator.ui.theme.primaryContainer
import com.example.quotegenerator.ui.theme.subFont

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        textStyle = MaterialTheme.typography.bodySmall,
        onValueChange = { onValueChange(it) },
        modifier = modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = primaryContainer
        ),
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Search
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.type_something_here_to_search),
                style = MaterialTheme.typography.bodySmall,
                color = subFont
            )
        },
        trailingIcon = {
            IconButton(
                onClick = { onValueChange("") },
            ) {
                if (value != "") {
                    Icon(
                        painter = painterResource(id = R.drawable.x),
                        contentDescription = "Clear",
                    )
                }
            }
        },
    )
}


@Preview
@Composable
fun SearchTextFieldPreview() {
    QuoteGeneratorTheme {
        SearchTextField(value = "", onValueChange = {})
    }
}

