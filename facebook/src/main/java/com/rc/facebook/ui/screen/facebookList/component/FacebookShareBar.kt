package com.rc.facebook.ui.screen.facebookList.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FacebookShareBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp, end = 20.dp, start = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier
                .clip(CircleShape)
                .size(30.dp)
                .background(Color.Black),
            painter = painterResource(id = android.R.drawable.ic_menu_info_details),
            contentDescription = null
        )

        Box(modifier = Modifier.padding(start = 4.dp)) {
            Text(
                text = "What's on your mind?",
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            modifier = Modifier.size(20.dp),
            painter = painterResource(id = android.R.drawable.ic_menu_camera),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
@Preview
private fun PreviewFacebookShareBar() {
    FacebookShareBar()
}