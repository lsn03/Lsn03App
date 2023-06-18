package com.example.lsn03app.domain.usecase.TaskListUseCase

import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class GetAllTaskListUseCase (private val TaskListRepository: ITaskListRepository) {
	suspend fun execute() :List<TaskList> {
		return TaskListRepository.getAllTaskLists()
	}
}