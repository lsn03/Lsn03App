package com.example.lsn03app.domain.usecase

import com.example.lsn03app.domain.IRepository
import com.example.lsn03app.domain.models.TaskList

class GetAllTaskListUseCase (private val TaskListRepository: IRepository) {
	suspend fun execute() :List<TaskList> {
		return TaskListRepository.getAllTaskLists()
	}
}