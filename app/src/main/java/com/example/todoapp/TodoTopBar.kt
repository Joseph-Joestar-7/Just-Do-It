package com.example.todoapp

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TodoTopBar(modifier: Modifier = Modifier){
     CenterAlignedTopAppBar(
         title ={
             Row(verticalAlignment = Alignment.CenterVertically){
                 Text(text= stringResource(R.string.app_name),
                     style=MaterialTheme.typography.headlineLarge,
                     fontSize = 40.sp)

             }
         }
     )
}