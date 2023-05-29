package com.example.lsn03app.data

import com.example.lsn03app.data.room.dao.TaskListDao
import com.example.lsn03app.data.room.entity.TaskListEntity
import com.example.lsn03app.domain.IRepository
import com.example.lsn03app.domain.models.TaskList
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TaskListRepository(private val taskListDao: TaskListDao):IRepository {
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
		taskListDao.deleteTaskList(mapper.taskListToTaskListEntity(taskList))
	}

	override suspend fun updateTaskList(taskList: TaskList) {
		taskListDao.updateTaskList(mapper.taskListToTaskListEntity(taskList))
	}

	override suspend fun getAllTaskLists(): List<TaskList> {
		return taskListDao.getTaskList().map {
			TaskList(it.name, it.id)
		}
	}


}