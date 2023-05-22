package com.example.lsn03app.data

import com.example.lsn03app.data.room.TaskListDao
import com.example.lsn03app.data.room.TaskListEntity
import com.example.lsn03app.domain.IRepository
import com.example.lsn03app.domain.models.TaskList

class TaskListRepository(private val taskListDao: TaskListDao):IRepository {
	override suspend fun addTaskList(taskList: TaskList) {
		taskListDao.addTaskList(taskListToTaskListEntity(taskList))
	}

	override suspend fun deleteTaskList(taskList: TaskList) {
		taskListDao.deleteTaskList(taskListToTaskListEntity(taskList))
	}

	override suspend fun updateTaskList(taskList: TaskList) {
		taskListDao.updateTaskList(taskListToTaskListEntity(taskList))
	}

	override suspend fun getAllTaskLists(): List<TaskList> {
		return taskListDao.getTaskList().map {
			TaskList(it.name, it.id)
		}
	}

	private fun taskListToTaskListEntity(taskList: TaskList) : TaskListEntity {
		return TaskListEntity(taskList.id, taskList.name)
	}
}