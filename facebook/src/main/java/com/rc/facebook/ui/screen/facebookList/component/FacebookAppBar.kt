package com.rc.facebook.ui.screen.facebookList.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rc.base.theme.extendColor
import com.rc.base.util.getCustomColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FacebookAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        modifier = Modifier.padding(horizontal = 0.dp).fillMaxWidth(),
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.extendColor.facebookDarkGrey,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White,
            scrolledContainerColor = getCustomColor().facebookDarkGrey,
        ),
        title = { Text(modifier = Modifier.fillMaxWidth(), text = "facebook") },
        actions = {
            FacebookAppBarActionButton()
        }
    )
}


@Composable
fun FacebookAppBarActionButton() {
    Row(modifier = Modifier.padding(end = 8.dp)) {
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = android.R.drawable.ic_input_add),
            contentDescription = null
        )
        Spacer(Modifier.size(10.dp))
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = android.R.drawable.ic_search_category_default),
            contentDescription = null
        )
        Spacer(Modifier.size(10.dp))
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = android.R.drawable.ic_menu_send),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PreviewFacebookAppBar() {
    FacebookAppBar(TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState()))
}