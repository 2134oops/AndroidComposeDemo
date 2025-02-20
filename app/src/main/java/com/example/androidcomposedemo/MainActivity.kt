package com.example.androidcomposedemo

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.rc.base.common.rememberRcAppState
import org.koin.android.java.KoinAndroidApplication
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinAndroidContext {
                val appState = rememberRcAppState()
                MyApp(appState)
            }
        }
    }
}
