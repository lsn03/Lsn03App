package com.example.lsn03app.domain.usecase

import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class AddTaskListUseCase(private val taskListRepository:ITaskListRepository) {
	suspend fun execute(name:String){
		val taskList = TaskList(name);
		taskListRepository.addTaskList(taskList);
	}
}