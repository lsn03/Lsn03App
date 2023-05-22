package com.example.lsn03app.domain

import com.example.lsn03app.domain.models.TaskList

interface IRepository {
	suspend fun addTaskList(taskList: TaskList)
	suspend fun deleteTaskList(taskList: TaskList)
	suspend fun updateTaskList(taskList: TaskList)
	suspend fun getAllTaskLists():List<TaskList>
}