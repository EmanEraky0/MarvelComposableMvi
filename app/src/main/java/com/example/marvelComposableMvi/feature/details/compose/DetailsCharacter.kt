package com.example.marvelComposableMvi.feature.details.compose

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import coil.compose.rememberAsyncImagePainter
import com.example.marvelComposableMvi.R
import com.example.marvelComposableMvi.feature.details.state.ResourceState
import com.example.marvelComposableMvi.feature.marvelList.compose.Loading
import com.example.marvelComposableMvi.feature.marvelList.domain.models.Character
import com.example.marvelComposableMvi.feature.marvelList.domain.models.Item
import com.example.marvelComposableMvi.feature.marvelList.domain.models.ListImage
import com.example.marvelComposableMvi.ui.theme.MarvelComposableMviTheme

@Composable
fun DetailsCharacter(character: Character, sectionState: LiveData<ResourceState>) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 16.dp)
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
        )

        Text(
            text = stringResource(id = R.string.name),
            fontSize = 18.sp,
            color = Color.Red,
            modifier = Modifier.padding(10.dp)
        )
        Text(
            text = character.name,
            fontSize = 15.sp,
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp)
        )

        if (character.description.isNotEmpty()) {
            Text(
                text = stringResource(id = R.string.description),
                fontSize = 18.sp,
                color = Color.Red,
                modifier = Modifier.padding(10.dp)
            )
            Text(
                text = character.description,
                fontSize = 15.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 10.dp)
            )
        }

        character.comics?.items?.let { SectionCharacter("Comics", sectionState) }
        character.series?.items?.let { SectionCharacter("Series", sectionState) }
        character.stories?.items?.let { SectionCharacter("Stories", sectionState) }
        character.events?.items?.let { SectionCharacter("Events", sectionState) }
    }
}

@Composable
fun SectionCharacter(
    nameSection: String,
    sectionState: LiveData<ResourceState>,
) {
    val state = sectionState.observeAsState().value

    val sectionItems = when (nameSection) {
        "Comics" -> state?.comics
        "Series" -> state?.series
        "Stories" -> state?.stories
        "Events" -> state?.events
        else -> emptyList()
    }
    val indexImage = remember { mutableIntStateOf(0) }
    val showImageSlider = remember { mutableStateOf(false) }
    val selectedSectionItems = remember { mutableStateOf<List<Item>>(emptyList()) }

    if (showImageSlider.value) {
        // Show ImageSlider
        ImageSlider(sectionList = selectedSectionItems.value,indexImage.value) {
            showImageSlider.value = false // Close slider on back action
        }

    } else {
        if ((state?.isLoadingComics == true || state?.isLoadingSeries == true || state?.isLoadingEvent == true || state?.isLoadingStories == true) && sectionItems.isNullOrEmpty()) {
            Loading()
        } else if (!(state?.errorMessage.isNullOrBlank()) && sectionItems.isNullOrEmpty()) {
            Text(
                text = state?.errorMessage ?: "",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            if (!sectionItems.isNullOrEmpty()) {
                Column(modifier = Modifier.padding(start = 10.dp)) {
                    Text(
                        text = nameSection,
                        fontSize = 18.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 10.dp, bottom = 5.dp)
                    )
                    LazyRow {
                        itemsIndexed(sectionItems) { index, item ->
                            CardSection(item){
                                indexImage.value =index
                                selectedSectionItems.value = sectionItems
                                showImageSlider.value =true
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CardSection(item: Item,  onClick: (Item) -> Unit) {

    Column(Modifier.clickable {onClick(item) }) {
        val painter = rememberAsyncImagePainter(model = item.resourceURI.replace("http", "https"))

        Image(
            painter = painter,
            contentDescription = "url section",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(end = 10.dp)
                .width(100.dp)
                .height(150.dp),
        )

        Text(
            modifier = Modifier.width(100.dp),
            text = item.name,
            fontSize = 13.sp,
            color = Color.Black,
            lineHeight = TextUnit.Unspecified,
            maxLines = 2
        )
    }

}


@Preview(showBackground = true)
@Composable
fun DetailsPreview() {
    val items: ArrayList<Item> = arrayListOf()
    items.add(
        Item(
            name = "Marvel",
            resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/57ed5bc9040e3.jpg"
        )
    )
    items.add(
        Item(
            name = "Marvel",
            resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/57ed5bc9040e3.jpg"
        )
    )
    items.add(
        Item(
            name = "Marvel",
            resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/57ed5bc9040e3.jpg"
        )
    )
    items.add(
        Item(
            name = "Marvel",
            resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/57ed5bc9040e3.jpg"
        )
    )
    items.add(
        Item(
            name = "Marvel",
            resourceURI = "http://i.annihil.us/u/prod/marvel/i/mg/9/50/57ed5bc9040e3.jpg"
        )
    )

    val list = ListImage(items = items)
    val sts = MutableLiveData(ResourceState())
    MarvelComposableMviTheme {
        val char = Character(series = list, comics = list, stories = list)
        DetailsCharacter(char, sts)
    }

}

