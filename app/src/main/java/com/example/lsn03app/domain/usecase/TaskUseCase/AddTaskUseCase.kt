package com.example.lsn03app.domain.usecase.TaskUseCase

import com.example.lsn03app.data.repositoryImpl.TaskRepository
import com.example.lsn03app.domain.models.Task

class AddTaskUseCase (private val taskRepository: TaskRepository) {
	suspend fun execute(task: Task){
		taskRepository.addTask(task);
	}
}