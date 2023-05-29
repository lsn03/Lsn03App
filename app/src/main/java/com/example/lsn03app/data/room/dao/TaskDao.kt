package com.example.lsn03app.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lsn03app.data.room.entity.TaskEntity
@Dao
abstract class TaskDao {
	@Insert
	abstract suspend fun addTask(taskEntity: TaskEntity)
	@Delete
	abstract suspend fun deleteTask(taskEntity:TaskEntity)
		@Query("SELECT * FROM task WHERE taskListId = :taskListId")
	abstract suspend fun getTasksFromTaskList(taskListId: Int): List<TaskEntity>
}