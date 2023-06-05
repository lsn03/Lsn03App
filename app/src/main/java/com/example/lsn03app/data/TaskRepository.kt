package com.example.lsn03app.data

import com.example.lsn03app.data.room.dao.TaskDao
import com.example.lsn03app.domain.ITaskRepository
import com.example.lsn03app.domain.models.Task
import com.example.lsn03app.domain.models.TaskList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskRepository(private val taskDao: TaskDao) :ITaskRepository {
	private val mapper = Mapper()
//	init {
//		for (i in 0..10){
//			GlobalScope.launch {
//				addTask(Task("NAME: $i","Text",i+1))
//				addTask(Task("NAMfddffdE_2: $i","Textfdfddf_2",i+1))
//			}
//		}
//
//
//	}

	override suspend fun addTask(task: Task) {
		taskDao.addTask(mapper.taskToTaskEntity(task))
	}

	override suspend fun deleteTask(task: Task) {
		taskDao.deleteTask(mapper.taskToTaskEntity(task))
	}

	override suspend fun getTasksFromTaskList(id: Int): List<Task> {
		return taskDao.getTasksFromTaskList(id).map {
			mapper.taskEntityToTask(it)
		}
	}

	override suspend fun addTaskToFavouriteTaskList(task: Task) {
		taskDao.addTaskToFavouriteTaskList(mapper.taskToTaskEntity(task).id)

	}

	override suspend fun removeTaskFromFavouriteTaskList(task: Task) {
		taskDao.removeTaskFromFavouriteTaskList(mapper.taskToTaskEntity(task).id)
	}

	override suspend fun getFavouriteTasks(): List<Task> {
		return taskDao.getFavouriteTasks().map {
			mapper.taskEntityToTask(it)
		}
	}

	override suspend fun updateTask(task: Task) {
		taskDao.updateTask(mapper.taskToTaskEntity(task))
	}


}