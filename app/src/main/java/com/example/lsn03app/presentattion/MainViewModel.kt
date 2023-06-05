package com.example.lsn03app.presentattion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.Task
import com.example.lsn03app.domain.models.TaskList
import com.example.lsn03app.domain.usecase.*
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {


	val taskLists = MutableLiveData<List<TaskList>>()
	private val taskListRepository = Dependencies.taskListRepository
	private val taskRepository = Dependencies.taskRepository
	private val addTaskListUseCase = AddTaskListUseCase(taskListRepository)
	private val getAllTaskListUseCase = GetAllTaskListUseCase(taskListRepository)
	private val deleteTaskListUseCase = DeleteTaskListUseCase(taskListRepository)
	private val getFavouriteTasksUseCase = GetFavouriteTasksUseCase(taskRepository)
	private val deleteTaskUseCase = DeleteTaskFromTaskListUseCase(taskRepository)

	fun addTaskList(name:String){
		viewModelScope.launch {
			addTaskListUseCase.execute(name);
		}
		getAllTaskList()

	}
	fun getFavouriteTaskList(){
		viewModelScope.launch {
			val tasks = getFavouriteTasksUseCase.execute()

		}
	}
	fun getAllTaskList(){
		viewModelScope.launch {
			taskLists.postValue(getAllTaskListUseCase.execute())
		}
	}
	fun removeTaskList(taskList: TaskList){
		viewModelScope.launch {
			deleteTaskListUseCase.execute(taskList)
		}
	}
	fun deleleteTaskFromTaskList(task:Task){
		viewModelScope.launch {
			deleteTaskUseCase.execute(task)
		}
	}
}