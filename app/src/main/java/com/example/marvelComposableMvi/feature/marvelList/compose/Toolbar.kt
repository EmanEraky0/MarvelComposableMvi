package com.example.marvelComposableMvi.feature.marvelList.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.marvelComposableMvi.R
import com.example.marvelComposableMvi.ui.theme.MarvelComposableMviTheme


@Composable
fun ToolbarWithSearch(onClickActionSearch: (String) -> Unit, onClickCancel: () -> Unit) {
    // State to toggle between Toolbar and SearchBar
    val isSearchActive = remember { mutableStateOf(false) }

    if (isSearchActive.value) {
        // Show the SearchBar when active
        openSearch(onClickCancel = {
            isSearchActive.value = false
            onClickCancel()
        }, onClickActionSearch = onClickActionSearch)


    } else {
        // Show the Toolbar by default
        Toolbar(onClickBack = { isSearchActive.value = true })
    }
}

@Composable
fun Toolbar(onClickBack: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween, // Space between Text and Image
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "MarvelApp",
            color = Color.White,
            modifier = Modifier.background(color = Color.Red),
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(painter = painterResource(id = R.drawable.ic_search), contentDescription = "search",
            modifier = Modifier
                .padding(10.dp)
                .clickable { onClickBack() }
        )
    }
}

@Composable
fun openSearch(onClickCancel: () -> Unit, onClickActionSearch: (String) -> Unit) {
    val textFieldValue = remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween, // Space between Text and Image
        verticalAlignment = Alignment.CenterVertically
    ) {

        TextField(
            value = textFieldValue.value,
            onValueChange = { textFieldValue.value = it },
            placeholder = {
                Text(
                    text = "Search ....",
                    fontSize = 15.sp,
                )
            },
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .padding(start = 10.dp, end = 10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Blue
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onClickActionSearch(textFieldValue.value.text)
                    println("Searching for: ${textFieldValue.value.text}")
                }
            )
        )


        Text(
            text = "Cancel",
            color = Color.Red,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(end = 10.dp)
                .clickable { onClickCancel() },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ToolbarPreview() {
    MarvelComposableMviTheme {
        ToolbarWithSearch({},{})
    }
}