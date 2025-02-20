package com.rc.facebook.ui.screen.facebookList

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.rc.base.common.RcAppState
import com.rc.base.util.getCustomColor
import com.rc.facebook.ui.screen.facebookList.component.FacebookAppBar
import com.rc.facebook.ui.screen.facebookList.component.FacebookDivider
import com.rc.facebook.ui.screen.facebookList.component.FacebookPostItemView
import com.rc.facebook.ui.screen.facebookList.component.FacebookShareBar
import com.rc.facebook.ui.screen.facebookList.component.FacebookStoryHorizontalView
import com.rc.facebook.ui.screen.facebookList.model.FacebookStoryCreateStoryItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStorySearchMusicItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStoryUserItem
import com.rc.facebook.ui.screen.facebookList.viewModel.FacebookListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FacebookScreen(appState: RcAppState, viewModel:FacebookListViewModel) {
    val isRefreshing = viewModel.isRefreshingState.collectAsState()
    FacebookScreenContent(isRefreshing = isRefreshing.value, onRefresh = viewModel::onRefresh)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FacebookScreenContent(isRefreshing: Boolean, onRefresh:()-> Unit ) {
    val scrollBehavior =
        TopAppBarDefaults.enterAlwaysScrollBehavior(state = rememberTopAppBarState())


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = getCustomColor().facebookDarkGrey,
        topBar = { FacebookAppBar(scrollBehavior) },
    ) {
        val contentPadding = it
        PullToRefreshBox(isRefreshing = isRefreshing, onRefresh = onRefresh,
            modifier = Modifier.padding(top = contentPadding.calculateTopPadding())) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    FacebookShareBar()
                }
                item {
                    FacebookDivider()
                }
                item {
                    FacebookStoryHorizontalView(
                        listOf(
                            FacebookStorySearchMusicItem(),
                            FacebookStoryCreateStoryItem(),
                            FacebookStoryUserItem(),
                            FacebookStoryUserItem(),
                            FacebookStoryUserItem(),
                            FacebookStoryUserItem()
                        )
                    )
                }
                item {
                    FacebookDivider()
                }

                items(100) {
                    FacebookPostItemView()
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewFaceBookContent() {
    FacebookScreenContent(false) {}
}
