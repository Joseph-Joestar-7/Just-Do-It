package com.example.todoapp

import android.os.Build
import android.widget.Space
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Locale


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(viewModel: TodoViewModel){
    val context = LocalContext.current
    val todoList by viewModel.todoList.observeAsState(emptyList())
    var inputText by remember{
        mutableStateOf("")
    }
    var isInputVisible by remember { mutableStateOf(false) } // State to track visibility
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isInputVisible = !isInputVisible },
                modifier = Modifier
                    .padding(16.dp),
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_circle_outline_24),
                        contentDescription = "Add Task",
                        tint = Color.White
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ){it->
        Column(modifier= Modifier
            .fillMaxHeight()
            .padding(top = 100.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
        )
        {
            if(isInputVisible) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    OutlinedTextField(
                        value = inputText, onValueChange = {
                            inputText = it
                        },
                        placeholder = {
                            Text(text = "What needs to be done?")
                        },
                        modifier = Modifier
                            .weight(3f)
                            .padding(end = 8.dp)
                    )
                    Button(
                        onClick = {
                            if (inputText != "") {
                                viewModel.addTodo(inputText)
                                inputText = ""
                                isInputVisible=false
                            } else {
                                Toast.makeText(context, "Enter a task Baka", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(), text = "ADD",
                            style = MaterialTheme.typography.displayLarge
                        )
                    }
                }
            }
            if (todoList.isNotEmpty()) {
                LazyColumn(
                    content = {
                        itemsIndexed(todoList) { index: Int, item: Todo ->
                            TodoItem(
                                item = item,
                                onDelete = {
                                    viewModel.deleteTodo(item.id)
                                },
                                onEdit = { newTitle -> viewModel.updateTodo(item.id, newTitle) }
                            )
                        }
                    }
                )
            }else {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Empty just like your DMs",
                    fontSize = 50.sp,
                    style = MaterialTheme.typography.displaySmall
                )
            }
        }
    }
}

@Composable
fun TodoItem(item: Todo,
             onDelete:()->Unit,
             onEdit: (String) -> Unit){
    var isEditing by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf(item.title) }
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        if(isEditing){
            OutlinedTextField(
                value = editText,
                onValueChange = { editText = it },
                modifier = Modifier.weight(1f),
                singleLine = true
            )
            IconButton(onClick = {
                onEdit(editText)
                isEditing = false
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.check_circle_svgrepo_com),
                    contentDescription = "Save",
                    tint = Color.Black
                )
            }
        }
        else{
            Column(
                modifier = Modifier.weight(1f)
            ){
                Text(text= SimpleDateFormat("HH:mm:aa, dd/MM(MMMM)", Locale.ENGLISH).format(item.createdAt),
                    fontSize = 12.sp,
                    color= Color.DarkGray
                )
                Text(text=item.title, fontSize = 16.sp,color =Color.Blue,
                    style = MaterialTheme.typography.displayMedium)
            }
        }

        IconButton(onClick = { isEditing = true }) {
            Icon(
                painter = painterResource(id = R.drawable.pencil_svgrepo_com),
                contentDescription = "Edit",
                tint = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(id = R.drawable.baseline_backspace_24),
                contentDescription ="Delete",
                tint=Color.White)
        }

    }
}
