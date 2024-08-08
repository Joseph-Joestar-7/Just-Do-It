package com.example.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

open class TodoViewModel: ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()
    val todoList: LiveData<List<Todo>>
        get()=todoDao.getAllTodo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title:String){
        viewModelScope.launch(Dispatchers.IO){
            todoDao.addTodo(Todo(title=title, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteTodo(id:Int){
        viewModelScope.launch(Dispatchers.IO){

        todoDao.deleteTodo(id)}
    }
}