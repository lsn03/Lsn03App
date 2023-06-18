package com.example.lsn03app.domain.usecase.TaskListUseCase

import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class AddTaskListUseCase(private val taskListRepository:ITaskListRepository) {
	suspend fun execute(taskList: TaskList){
		taskListRepository.addTaskList(taskList);
	}
}