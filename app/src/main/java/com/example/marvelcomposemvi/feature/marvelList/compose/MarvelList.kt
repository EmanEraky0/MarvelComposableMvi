package com.example.marvelcomposablemvi.feature.marvelList.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import coil.compose.rememberAsyncImagePainter
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Character
import com.example.marvelcomposablemvi.feature.marvelList.domain.models.Thumbnail
import com.example.marvelcomposablemvi.feature.marvelList.intent.MarvelIntent
import com.example.marvelcomposablemvi.feature.marvelList.state.MarvelState
import com.example.marvelcomposablemvi.ui.theme.MarvelComposeMviTheme

@Composable
fun MarvelScreen(state:LiveData<MarvelState> ,sendIntent: (MarvelIntent) -> Unit,onCharacterClick: (Character) -> Unit ) {
    val movieState = state.observeAsState().value
    movieState?.let {
        when {
            it.isLoading && it.characters.isEmpty() -> {
                Loading() // Show loading only for the initial load
            }

            it.characters.isNotEmpty() -> {
                MarvelList(
                    itemList = it.characters,
                    onCharacterClick = onCharacterClick,
                    onEndOfListReached = { sendIntent(MarvelIntent.LoadMore) }
                )
            }

            it.errorMessage != null -> {
                Text(text = it.errorMessage ?: "Error", color = Color.Red)
            }

            else -> {}
        }
    }


}

@Composable
fun MarvelList(
    itemList: List<Character>,
    onCharacterClick: (Character) -> Unit,
    onEndOfListReached: () -> Unit
) {
    val scrollState = rememberLazyListState()

    LazyColumn(state = scrollState) {
        items(items = itemList) { character ->
            ItemMarvel(character, onCharacterClick)
        }

        item {
            if (scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == itemList.size - 1 &&
                scrollState.isScrollInProgress
            ) {
                onEndOfListReached()
            }
        }
    }
}

@Composable
fun Loading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()

    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ItemMarvel(character: Character, onCharacterClick: (Character) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = character.thumbnail?.path?.replace(
                    "http",
                    "https"
                ) + "." + character.thumbnail?.extension
            ),
            contentDescription = "url Marvel",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable { onCharacterClick(character) }
        )
        Text(
            text = character.name,
            fontSize = 18.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomStart)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(10.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun MarvelPreview() {
    MarvelComposeMviTheme {
        val ch = Character(
            name = "Green Man",
            thumbnail = Thumbnail(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/57ed5bc9040e3",
                extension = "jpg"
            )
        )
        val list = listOf(ch, ch, ch, ch, ch, ch, ch, ch, ch, ch)
        MarvelList(list,{},{})
    }


}