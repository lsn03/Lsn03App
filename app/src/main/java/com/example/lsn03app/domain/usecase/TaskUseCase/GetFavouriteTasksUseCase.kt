package com.example.lsn03app.domain.usecase.TaskUseCase

import com.example.lsn03app.data.repositoryImpl.TaskRepository
import com.example.lsn03app.domain.models.Task

class GetFavouriteTasksUseCase (private val taskRepository: TaskRepository) {
	suspend fun execute():List<Task>{
		return taskRepository.getFavouriteTasks();
	}
}