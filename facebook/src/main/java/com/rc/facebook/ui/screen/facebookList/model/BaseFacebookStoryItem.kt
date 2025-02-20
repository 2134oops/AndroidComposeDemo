package com.rc.facebook.ui.screen.facebookList.model

enum class FacebookStoryType {
    USER_STORY,
    CREATE_STORY,
    MUSIC_STORY,
}

abstract class BaseFacebookStoryItem {
    abstract val type: FacebookStoryType
}