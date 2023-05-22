package com.example.lsn03app.data.room

import androidx.room.*

@Dao
abstract class TaskListDao {
	@Insert
	abstract  suspend fun addTaskList(taskListEntity: TaskListEntity)

	@Delete
	abstract suspend fun deleteTaskList(taskListEntity: TaskListEntity)

	@Update
	abstract suspend fun updateTaskList(taskListEntity: TaskListEntity)

	@Query("SELECT * FROM taskList")
	abstract suspend fun getTaskList():List<TaskListEntity>
}