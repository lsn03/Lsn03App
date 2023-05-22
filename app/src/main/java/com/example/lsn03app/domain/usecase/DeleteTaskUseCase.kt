package com.example.lsn03app.domain.usecase

import com.example.lsn03app.domain.IRepository
import com.example.lsn03app.domain.models.TaskList

class DeleteTaskUseCase(private val taskListRepository: IRepository) {
	suspend fun execute(taskList: TaskList){
		taskListRepository.deleteTaskList(taskList);
	}
}