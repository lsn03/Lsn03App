package com.example.lsn03app.domain

import com.example.lsn03app.domain.models.Task
import com.example.lsn03app.domain.models.TaskList

interface ITaskRepository {
	suspend fun addTask(task:Task)
	suspend fun deleteTask(task:Task)
	suspend fun getTasksFromTaskList(taskList: TaskList) : List<Task>
}