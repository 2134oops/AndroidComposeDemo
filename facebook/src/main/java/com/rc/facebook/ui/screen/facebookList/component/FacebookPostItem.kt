package com.rc.facebook.ui.screen.facebookList.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.rc.facebook.R
import com.rc.facebook.ui.screen.facebookList.model.EmojiItem
import com.rc.facebook.ui.screen.facebookList.model.EmojiType


val listEmoji = listOf(
    EmojiItem(
        emojiType = EmojiType.LIKE,
        count = 10
    ),
    EmojiItem(
        emojiType = EmojiType.LIKE,
        count = 1
    ),
    EmojiItem(
        emojiType = EmojiType.LOVE,
        count = 10
    ), EmojiItem(
        emojiType = EmojiType.LOVE,
        count = 10
    ),
    EmojiItem(
        emojiType = EmojiType.ANGRY,
        count = 10
    ),
    EmojiItem(
        emojiType = EmojiType.SAD,
        count = 5
    ), EmojiItem(
        emojiType = EmojiType.SMILE,
        count = 19
    )

)

@Composable
fun FacebookPostItemView(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserInfoView(modifier.weight(1f))
            Icon(
                modifier = Modifier
                    .rotate(90f)
                    .size(20.dp),
                imageVector = Icons.Default.MoreVert,
                contentDescription = null,
                tint = Color.LightGray
            )
            Spacer(modifier = Modifier.size(8.dp))
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Default.Close,
                contentDescription = null,
                tint = Color.LightGray
            )
        }

        Box(modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)) {
            Text(
                "content content content content content content content content content content content content content content content content ",
                color = Color.White,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .background(Color(0x55ff9933)),
            imageVector = Icons.Default.AccountBox,
            contentDescription = null
        )

        UserReactionStatusView(emojiItemList = listEmoji)
        BottomButtonBar()
        FacebookDivider()
    }

}


@Composable
fun UserReactionStatusView(
    modifier: Modifier = Modifier,
    commentCount: Int = 1,
    sharesCount: Int = 1,
    emojiItemList: List<EmojiItem> = listEmoji,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        if (emojiItemList.isNotEmpty()) {
            LikeCountView(emojiItemList = emojiItemList)
        }

        if (commentCount > 0 || sharesCount > 0) {
            CommentCountView(commentCount = commentCount, sharesCount = sharesCount)
        }
    }
}

@Composable
fun LikeCountView(modifier: Modifier = Modifier, emojiItemList: List<EmojiItem>) {
    val totalCount = emojiItemList.sumOf { it.count }
    val emojiList =
        emojiItemList.filter { it.count > 0 }.sortedBy { it.count.dec() }.groupBy { it.emojiType }
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        var offsetX = (-5).dp
        emojiList.onEachIndexed { index, it ->
            val displayIconId = when (it.key) {
                EmojiType.SMILE -> R.drawable.haha
                EmojiType.SAD -> R.drawable.sad
                EmojiType.ANGRY -> R.drawable.angry
                EmojiType.LOVE -> R.drawable.love
                EmojiType.LIKE -> R.drawable.like
            }
             offsetX = if (index > 0) {
                 offsetX -5.dp
            } else {
                0.dp
            }
            val zIndex = emojiList.size - index
            Image(
                painter = painterResource(displayIconId),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .offset(x = offsetX)
                    .zIndex(zIndex.toFloat()),

            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        Text("$totalCount",modifier = Modifier.offset(x = offsetX), style = TextStyle(fontSize = 12.sp, color = Color.LightGray))
    }
}

@Composable
fun CommentCountView(
    modifier: Modifier = Modifier,
    commentCount: Int = 1,
    sharesCount: Int = 1,
) {
    val textStyle = TextStyle(fontSize = 12.sp, color = Color.LightGray)
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = modifier) {
        if (commentCount > 0) {
            Text("$commentCount comments", style = textStyle)
        }
        if (sharesCount > 0) {
            Text("$sharesCount shares", style = textStyle)
        }
    }
}

@Preview
@Composable
private fun PreviewUserReactionStatusView() {
    UserReactionStatusView()
}


@Composable
fun UserInfoView(modifier: Modifier) {
    Row(modifier) {
        Icon(
            modifier = Modifier
                .size(30.dp)
                .border(2.dp, color = Color.Blue, shape = CircleShape)
                .padding(1.dp),
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column {
            Text(
                text = "username",
                color = Color.White,
                style = TextStyle(
                    fontSize = 14.sp
                )
            )
            Spacer(modifier=Modifier.height(2.dp))
            Row {
                Text(
                    "29m ．",
                    color = Color.LightGray,
                    style = TextStyle(
                        fontSize = 8.sp
                    )
                )
                Icon(
                    modifier = Modifier.size(12.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun BottomButtonBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 8.dp),
    ) {
        BottomButton(
            modifier = Modifier.weight(1f),
            buttonIcon = R.drawable.ic_like_stroke,
            buttonText = "Like"
        )
        BottomButton(
            modifier = Modifier.weight(1f),
            buttonIcon = R.drawable.ic_comment,
            buttonText = "Comment"
        )
        BottomButton(
            modifier = Modifier.weight(1f),
            buttonIcon = android.R.drawable.ic_menu_send,
            buttonText = "Send"
        )
        BottomButton(
            modifier = Modifier.weight(1f),
            buttonIcon = android.R.drawable.ic_menu_share,
            buttonText = "Share"
        )
    }
}

@Composable
fun BottomButton(modifier: Modifier = Modifier, @DrawableRes buttonIcon: Int, buttonText: String) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier, horizontalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(buttonIcon),
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            colorFilter = ColorFilter.tint(Color.LightGray)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            text = buttonText,
            style = TextStyle(
                fontSize = 12.sp,
                color = Color.LightGray
            )
        )
    }
}

@Preview
@Composable
private fun PreviewBottomButtonBar() {
    BottomButtonBar()
}

@Preview
@Composable
private fun PreviewUserInfoView() {
    UserInfoView(Modifier)
}


@Preview
@Composable
private fun PreviewFacebookPostItemView() {
    FacebookPostItemView()
}
