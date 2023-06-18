package com.example.lsn03app.domain.usecase.TaskListUseCase

import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class GetTaskListByIdUseCase(private val taskListRepository: ITaskListRepository) {
	suspend fun execute(taskListId:Int): TaskList? {
		return taskListRepository.getTaskList(taskListId)
	}
}