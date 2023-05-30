package com.example.lsn03app.presentattion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.TaskList
import com.example.lsn03app.domain.usecase.AddTaskListUseCase
import com.example.lsn03app.domain.usecase.DeleteTaskUseCase
import com.example.lsn03app.domain.usecase.GetAllTaskListUseCase
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {


	val taskLists = MutableLiveData<List<TaskList>>()
	private val taskListRepository = Dependencies.taskListRepository

	private val addTaskListUseCase = AddTaskListUseCase(taskListRepository)
	private val getAllTaskListUseCase = GetAllTaskListUseCase(taskListRepository)
	private val deleteTaskUseCase = DeleteTaskUseCase(taskListRepository)

	fun addTaskList(name:String){
		viewModelScope.launch {
			addTaskListUseCase.execute(name);
		}
		getAllTaskList()

	}
	fun getAllTaskList(){
		viewModelScope.launch {
			taskLists.postValue(getAllTaskListUseCase.execute())
		}
	}
	fun removeTaskList(taskList: TaskList){
		viewModelScope.launch {
			deleteTaskUseCase.execute(taskList)
		}
	}
}