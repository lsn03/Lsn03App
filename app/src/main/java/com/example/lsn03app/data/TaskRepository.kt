package com.example.lsn03app.data

import com.example.lsn03app.data.room.dao.TaskDao
import com.example.lsn03app.domain.ITaskRepository
import com.example.lsn03app.domain.models.Task
import com.example.lsn03app.domain.models.TaskList

class TaskRepository(private val taskDao: TaskDao) :ITaskRepository {
	private val mapper = Mapper()
	override suspend fun addTask(task: Task) {
		taskDao.addTask(mapper.taskToTaskEntity(task))
	}

	override suspend fun deleteTask(task: Task) {
		taskDao.deleteTask(mapper.taskToTaskEntity(task))
	}

	override suspend fun getTasksFromTaskList(taskList: TaskList): List<Task> {
		return taskDao.getTasksFromTaskList(taskList.id).map {
			mapper.taskEntityToTask(it)
		}
	}

}