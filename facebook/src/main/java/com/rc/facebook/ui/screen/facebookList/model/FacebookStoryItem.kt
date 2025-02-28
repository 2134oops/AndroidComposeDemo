package com.rc.facebook.ui.screen.facebookList.model

data class FacebookStoryUserItem(override val type: FacebookStoryType = FacebookStoryType.USER_STORY, val userName:String) :
    BaseFacebookStoryItem()

data class FacebookStoryCreateStoryItem(override val type: FacebookStoryType = FacebookStoryType.CREATE_STORY) :
    BaseFacebookStoryItem()

data class FacebookStorySearchMusicItem(override val type: FacebookStoryType = FacebookStoryType.MUSIC_STORY) :
    BaseFacebookStoryItem()