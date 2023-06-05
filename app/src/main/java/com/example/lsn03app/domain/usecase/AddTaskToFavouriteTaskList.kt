package com.example.lsn03app.domain.usecase

import com.example.lsn03app.data.TaskRepository
import com.example.lsn03app.domain.models.Task

class AddTaskToFavouriteTaskList(private val taskRepository: TaskRepository) {
	suspend fun execute(task: Task){
		taskRepository.addTaskToFavouriteTaskList(task);
	}

}