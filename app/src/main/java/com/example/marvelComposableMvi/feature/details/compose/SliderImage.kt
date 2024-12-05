package com.example.marvelComposableMvi.feature.details.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.rememberAsyncImagePainter
import com.example.marvelComposableMvi.feature.marvelList.domain.models.Item
import com.google.accompanist.pager.HorizontalPagerIndicator


@Composable
fun ImageSlider(sectionList: List<Item>,indexImage :Int, onClose: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = indexImage,pageCount = { sectionList.size })

    Dialog(onDismissRequest = { onClose() }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top =16.dp, bottom =16.dp)
                .background(Color.Black.copy(alpha = 0.8f))
        ) {

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 20.dp)
            ) { page ->

                val item = sectionList[page]
//                Surface(
//                    modifier = Modifier
//                        .padding(16.dp)
//                        .fillMaxWidth(),
//                    shape = RoundedCornerShape(12.dp),
//                    color = MaterialTheme.colorScheme.surface,
//                    shadowElevation = 4.dp
//                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = item.resourceURI.replace("http","https")),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.White,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
//                }
            }

            // Indicator positioned at the center-bottom of the screen
            HorizontalPagerIndicator(
                pagerState = pagerState,
                pageCount = sectionList.size,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                activeColor = MaterialTheme.colorScheme.primary,
                inactiveColor = Color.Gray

            )

            Text(
                text = "Close",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .clickable { onClose() }
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun PreviewImageTextSlider() {
    val list = listOf(
        Item(
            name = "",
            resourceURI = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        ),
        Item(
            name = "",
            resourceURI = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        ),
        Item(
            name = "",
            resourceURI = "https://i.annihil.us/u/prod/marvel/i/mg/c/e0/535fecbbb9784.jpg"
        )
    )
    ImageSlider(sectionList = list,0) {}
}