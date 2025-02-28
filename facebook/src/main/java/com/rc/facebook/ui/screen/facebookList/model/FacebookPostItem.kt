package com.rc.facebook.ui.screen.facebookList.model

data class FacebookPostItem(
    val userName: String,
    val createTime: String,
    val postTitle: String,
    val emojiList: List<EmojiItem> = emptyList(),
    val commentCount: String,
    val sharedCount: String
)


enum class EmojiType {
    SMILE,
    SAD,
    ANGRY,
    LOVE,
    LIKE,
}

data class EmojiItem(
    val emojiType: EmojiType,
    val count: Int = 0
)