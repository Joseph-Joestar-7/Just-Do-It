package com.example.todoapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.ui.theme.TodoAppTheme
import java.time.Instant
import java.util.Date

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val todoViewModel= ViewModelProvider(this)[TodoViewModel::class.java]
        setContent {
            TodoAppTheme {
                    Scaffold(topBar = { TodoTopBar() }) { it->
                        Surface(modifier = Modifier.fillMaxSize()) {
                            TodoListPage(todoViewModel)
                        }
                    }
            }
        }
    }
}

