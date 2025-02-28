package com.rc.facebook.ui.screen.facebookList.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rc.facebook.ui.screen.facebookList.model.BaseFacebookStoryItem
import com.rc.facebook.ui.screen.facebookList.model.EmojiItem
import com.rc.facebook.ui.screen.facebookList.model.EmojiType
import com.rc.facebook.ui.screen.facebookList.model.FacebookPostItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStoryCreateStoryItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStorySearchMusicItem
import com.rc.facebook.ui.screen.facebookList.model.FacebookStoryUserItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FacebookListViewModel(application: Application) :
    AndroidViewModel(application = application) {

    private val listEmoji = listOf(
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

    private val _isRefreshingState: MutableStateFlow<Boolean> by lazy {
        MutableStateFlow(false)
    }
    val isRefreshingState: StateFlow<Boolean> get() = _isRefreshingState

    private val _facebookPostListStateFlow: MutableStateFlow<List<FacebookPostItem>> by lazy {
        MutableStateFlow(emptyList())
    }
    val facebookListState: StateFlow<List<FacebookPostItem>> get() = _facebookPostListStateFlow

    private val _storyListStateFlow: MutableStateFlow<List<BaseFacebookStoryItem>> by lazy {
        MutableStateFlow(listOf(FacebookStorySearchMusicItem(), FacebookStoryCreateStoryItem()))
    }
    val storyListState: StateFlow<List<BaseFacebookStoryItem>> get() = _storyListStateFlow


    init {
       viewModelScope.launch {
           fetchFacebookList(false)
       }
    }


    private suspend fun fetchFacebookList(isRefresh: Boolean) {
        createFacebookStoryList().let { userStoryList ->
            if (isRefresh) {
                userStoryList.shuffled()
            } else {
                userStoryList
            }
        }.let { resultList ->
            val displayStoryList = mutableListOf(FacebookStorySearchMusicItem(), FacebookStoryCreateStoryItem())
            displayStoryList.addAll(resultList)
            _storyListStateFlow.value = displayStoryList
        }

        createFacebookPostList().let { postList ->
            _facebookPostListStateFlow.value = if (isRefresh) postList.shuffled() else postList
        }

    }

    private fun createFacebookStoryList(): List<BaseFacebookStoryItem> {
        val list = mutableListOf<BaseFacebookStoryItem>()
        for (i in 0..10) {
            list.add(FacebookStoryUserItem(userName = "Friend $i"))
        }
        return list
    }

    private fun createFacebookPostList(): List<FacebookPostItem> {
        val list = mutableListOf<FacebookPostItem>()

        for (i in 0..10) {
            list.add(
                FacebookPostItem(
                    userName = "User $i",
                    createTime = "${i + 10}m",
                    postTitle = "Post $i",
                    commentCount = "10",
                    sharedCount = "20",
                    emojiList = listEmoji,
                )
            )
        }
        return list
    }


    fun onRefresh() {
        _isRefreshingState.value = true
        viewModelScope.launch(Dispatchers.IO) {
            delay(2000)
            fetchFacebookList(true)
            withContext(Dispatchers.Main) {
                _isRefreshingState.value = false
            }
        }
    }

}