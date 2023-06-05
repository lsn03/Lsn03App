package com.example.lsn03app.data.room.dao

import androidx.room.*
import com.example.lsn03app.data.room.entity.TaskEntity
@Dao
abstract class TaskDao {
	@Insert
	abstract suspend fun addTask(taskEntity: TaskEntity)

	@Delete
	abstract suspend fun deleteTask(taskEntity:TaskEntity)

	@Query("SELECT * FROM task WHERE taskListId = :taskListId")
	abstract suspend fun getTasksFromTaskList(taskListId: Int): List<TaskEntity>

	@Query("SELECT * FROM task WHERE isFavourite = true")
	abstract suspend fun getFavouriteTasks(): List<TaskEntity>

	@Query("UPDATE task set isFavourite = true where id = :taskId")
	abstract suspend fun addTaskToFavouriteTaskList(taskId: Int)

	@Query("UPDATE task set isFavourite = false where id = :taskId")
	abstract suspend fun removeTaskFromFavouriteTaskList(taskId: Int)
}