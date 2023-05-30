package com.example.lsn03app.presentattion

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.Task
import kotlinx.coroutines.launch
import com.example.lsn03app.domain.usecase.AddTaskListUseCase
import com.example.lsn03app.domain.usecase.GetAllTaskListUseCase

class TaskListViewModel:ViewModel() {
	val list = MutableLiveData<List<Task>>()
	private val taskRepository = Dependencies.taskRepository
	fun getTasksFromTaskList(taskListId: Int) {
		viewModelScope.launch {
			list.postValue(taskRepository.getTasksFromTaskList(taskListId))
		}
	}
}