package com.example.lsn03app.domain.usecase

import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class DeleteTaskListUseCase(private val taskListRepository: ITaskListRepository) {
	suspend fun execute(taskList: TaskList){
		taskListRepository.deleteTaskList(taskList);
	}
	suspend fun execute(id: Int){
		taskListRepository.deleteTaskList(id);
	}
}