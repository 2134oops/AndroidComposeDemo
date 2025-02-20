package com.rc.facebook.ui.screen

import com.rc.facebook.ui.screen.facebookList.FacebookScreenContent
import com.rc.facebook.ui.screen.facebookList.viewModel.FacebookListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val facebookModule = module {
    viewModel {
        FacebookListViewModel(
            application = get()
        )
    }
}