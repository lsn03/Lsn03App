package com.example.lsn03app.domain.usecase.TaskUseCase

import com.example.lsn03app.data.repositoryImpl.TaskRepository
import com.example.lsn03app.domain.models.Task

class GetTasksFromTaskListUseCase (private val taskRepository: TaskRepository) {
	suspend fun execute(id:Int) :List<Task> {
		return taskRepository.getTasksFromTaskList(id);
	}

}