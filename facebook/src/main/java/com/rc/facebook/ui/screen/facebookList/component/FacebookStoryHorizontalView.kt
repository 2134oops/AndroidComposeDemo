package com.rc.facebook.ui.screen.facebookList.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rc.facebook.ui.screen.facebookList.model.BaseFacebookStoryItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStoryCreateStoryItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStorySearchMusicItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStoryUserItem

@Composable
fun FacebookStoryHorizontalView(userStoryList: List<BaseFacebookStoryItem>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(16.dp),
        state = rememberLazyListState(initialFirstVisibleItemIndex = 1)
    ) {
        items(userStoryList.size) {
            userStoryList[it].let { item ->
                when (item) {
                    is FacebookStoryUserItem -> {
                        FacebookStoryUserItemView()
                    }

                    is FacebookStoryCreateStoryItem -> {
                        FacebookStoryCreateStoryItemView()
                    }

                    is FacebookStorySearchMusicItem -> {
                        FacebookStorySearchMusicItemView()
                    }
                }
            }
        }
    }
}


@Composable
fun FacebookStoryUserItemView() {
    StoryItemCardView {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0x99000000)),
                imageVector = Icons.Default.Delete,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Fit
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(6.dp)
            ) {
                Image(
                    modifier = Modifier
                        .size(30.dp)
                        .border(width = 2.dp, shape = CircleShape, color = Color.Blue)
                        .padding(2.dp),
                    imageVector = Icons.Default.Face,
                    contentDescription = null
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(horizontal = 12.dp, vertical = 12.dp)
            ) {
                Text(
                    text = "User Name User Name User Name User Name",
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 12.sp,
                    ),
                    maxLines = 2,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun FacebookStoryCreateStoryItemView() {
    StoryItemCardView {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize()) {
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .weight(6f)
                        .background(Color.Black)
                )
                Spacer(
                    Modifier
                        .fillMaxWidth()
                        .weight(4f)
                        .background(Color.Gray)
                )
            }

            Icon(
                modifier = Modifier
                    .align(
                        alignment = BiasAlignment(
                            horizontalBias = 0f,
                            verticalBias = 0.25f
                        )
                    )
                    .background(Color.White, shape = CircleShape)
                    .clip(CircleShape)
                    .border(2.dp, shape = CircleShape, color = Color.Gray),
                imageVector = Icons.Default.AddCircle,
                contentDescription = null,
                tint = Color.Blue
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 12.dp),
                text = "Create\nStory",
                color = Color.White,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontSize = 12.sp
                )
            )
        }
    }

}


@Composable
fun FacebookStorySearchMusicItemView() {
    StoryItemCardView {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(0xff07cb81),
                            Color(0xff1bbdca)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(40.dp)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(30.dp)
                            .offset(x = 2.dp),
                        painter = painterResource(id = android.R.drawable.ic_media_play),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = "Music",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontSize = 12.sp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFacebookStorySearchMusicItem() {
    FacebookStorySearchMusicItemView()

}

@Composable
@Preview
private fun PreviewFacebookStoryCreateStoryItem() {
    FacebookStoryCreateStoryItem()
}

@Composable
fun StoryItemCardView(
    contentScope: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(100.dp)
            .aspectRatio(9f / 16f), content = contentScope
    )
}

@Composable
@Preview
private fun PreviewStoryItemCardView() {
    StoryItemCardView {

    }
}

@Composable
@Preview
private fun PreviewFacebookStoryUserItem() {
    FacebookStoryUserItem()
}


@Composable
@Preview
private fun PreviewFacebookHorizontalView() {
    val sampleDataList = listOf(FacebookStoryUserItem())
    FacebookStoryHorizontalView(sampleDataList)
}
