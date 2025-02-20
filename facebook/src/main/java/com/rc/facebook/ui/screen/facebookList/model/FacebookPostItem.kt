package com.rc.facebook.ui.screen.facebookList.model

class FacebookPostItem {
}

enum class EmojiType {
    SMILE,
    SAD,
    ANGRY,
    LOVE,
    LIKE,
}

data class EmojiItem(
    val emojiType: EmojiType,
    val count:Int = 0
)