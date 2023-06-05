package com.example.lsn03app.data.room.dao

import androidx.room.*
import com.example.lsn03app.data.room.entity.TaskListEntity

@Dao
abstract class TaskListDao {
	@Insert
	abstract  suspend fun addTaskList(taskListEntity: TaskListEntity)

	@Delete
	abstract suspend fun deleteTaskList(taskListEntity: TaskListEntity)

	@Update
	abstract suspend fun updateTaskList(taskListEntity: TaskListEntity)

	@Query("SELECT * FROM taskList")
	abstract suspend fun getTaskListsEntity():List<TaskListEntity>

	@Query("SELECT * FROM taskList WHERE id = :taskListId")
	abstract suspend fun getTaskListEntity(taskListId:Int):TaskListEntity
}