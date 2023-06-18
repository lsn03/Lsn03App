package com.example.lsn03app.data.repositoryImpl

import com.example.lsn03app.data.Mapper
import com.example.lsn03app.data.room.dao.TaskListDao
import com.example.lsn03app.domain.ITaskListRepository
import com.example.lsn03app.domain.models.TaskList

class TaskListRepository(private val taskListDao: TaskListDao):ITaskListRepository {
	private val mapper = Mapper()

//	init {
//		for (i in 0..10){
//			GlobalScope.launch {
//				addTaskList(TaskList("NAME: $i"))
//			}
//		}
//
//
//	}

	override suspend fun addTaskList(taskList: TaskList) {
		taskListDao.addTaskList(mapper.taskListToTaskListEntity(taskList))
	}

	override suspend fun deleteTaskList(taskList: TaskList) {
		taskListDao.deleteTaskList(taskList.id)
		taskListDao.deleteTasksByTaskListId(taskList.id)
	}

	override suspend fun deleteTaskList(taskListId: Int) {
		taskListDao.deleteTaskList(taskListId)
		taskListDao.deleteTasksByTaskListId(taskListId)
	}

	override suspend fun updateTaskList(taskList: TaskList) {
		taskListDao.updateTaskList(mapper.taskListToTaskListEntity(taskList))
	}

	override suspend fun updateTaskList(taskListId: Int) {
		val taskListEntity = taskListDao.getTaskListEntity(taskListId)
		taskListDao.updateTaskList(taskListEntity)
	}

	override suspend fun getAllTaskLists(): List<TaskList> {
		return taskListDao.getTaskListsEntity().map {
			TaskList(it.name,it.isFavourite, it.id)
		}
	}

	override suspend fun getTaskList(taskListId: Int): TaskList {
		val taskListEntity = taskListDao.getTaskListEntity(taskListId)
		return taskListEntity?.let {
			mapper.taskListEntityToTaskList(it)


		}!!
	}


}