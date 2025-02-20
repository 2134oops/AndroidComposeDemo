package com.rc.facebook.ui.screen.facebookList.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FacebookDivider() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(Color.Black)
    )
}

@Preview
@Composable
private fun PreviewFacebookDivider() {
    FacebookDivider()
}