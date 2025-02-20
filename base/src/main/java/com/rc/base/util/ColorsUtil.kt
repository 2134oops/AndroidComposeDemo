package com.rc.base.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.rc.base.theme.RcColors
import com.rc.base.theme.extendColor

@Composable
fun getCustomColor(): RcColors = MaterialTheme.colorScheme.extendColor