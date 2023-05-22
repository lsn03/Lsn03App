package com.example.lsn03app.domain.usecase

import com.example.lsn03app.domain.IRepository
import com.example.lsn03app.domain.models.TaskList

class AddTaskListUseCase(private val taskListRepository:IRepository) {
	suspend fun execute(name:String){
		val taskList = TaskList(name);
		taskListRepository.addTaskList(taskList);
	}
}