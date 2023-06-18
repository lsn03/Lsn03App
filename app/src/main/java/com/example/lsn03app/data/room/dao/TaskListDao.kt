package com.example.lsn03app.data.room.dao

import androidx.room.*
import com.example.lsn03app.data.room.entity.TaskListEntity

@Dao
abstract class TaskListDao {
	@Insert
	abstract  suspend fun addTaskList(taskListEntity: TaskListEntity)

	@Query("DELETE from taskList where id = :taskListId")
	abstract suspend fun deleteTaskList(taskListId:Int)

	@Query("DELETE from Task where taskListId = :taskListId")
	abstract suspend fun deleteTasksByTaskListId(taskListId:Int)
	@Update
	abstract suspend fun updateTaskList(taskListEntity: TaskListEntity)

	@Query("SELECT * FROM taskList")
	abstract suspend fun getTaskListsEntity():List<TaskListEntity>

	@Query("SELECT * FROM taskList WHERE id = :taskListId")
	abstract suspend fun getTaskListEntity(taskListId:Int):TaskListEntity
}