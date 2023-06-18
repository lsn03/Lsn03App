package com.example.lsn03app.presentattion.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.TaskList
import com.example.lsn03app.domain.usecase.TaskListUseCase.AddTaskListUseCase
import com.example.lsn03app.domain.usecase.TaskListUseCase.DeleteTaskListUseCase
import com.example.lsn03app.domain.usecase.TaskListUseCase.GetAllTaskListUseCase
import com.example.lsn03app.domain.usecase.TaskListUseCase.RenameTaskListUseCase
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {


	val taskLists = MutableLiveData<List<TaskList>>()
	private val taskListRepository = Dependencies.taskListRepository
	private val addTaskListUseCase = AddTaskListUseCase(taskListRepository)
	private val getAllTaskListUseCase = GetAllTaskListUseCase(taskListRepository)
	private val deleteTaskListUseCase = DeleteTaskListUseCase(taskListRepository)
	private val renameTaskListUseCase = RenameTaskListUseCase(taskListRepository)

	fun addTaskList(taskList: TaskList){
		viewModelScope.launch {
			addTaskListUseCase.execute(taskList);
			getAllTaskList()
		}
	}
	fun renameTaskList(name: String,taskList: TaskList){
		viewModelScope.launch {
			renameTaskListUseCase.execute(taskList,name)
			getAllTaskList()
		}
	}
	fun getAllTaskList(){
		viewModelScope.launch {
			taskLists.postValue(getAllTaskListUseCase.execute())
		}
	}
	fun removeTaskList(taskListId: Int){
		viewModelScope.launch {
			deleteTaskListUseCase.execute(taskListId)
			getAllTaskList()
		}

	}

}