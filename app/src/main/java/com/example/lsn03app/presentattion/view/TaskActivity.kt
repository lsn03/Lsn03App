package com.example.lsn03app.presentattion.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.lsn03app.databinding.ActivityTaskBinding
import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.Task
import com.example.lsn03app.presentattion.viewModel.TaskViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import kotlin.Int as Int

class TaskActivity : AppCompatActivity() {

	lateinit var binding: ActivityTaskBinding
	private lateinit var mode: String
	lateinit var taskViewModel: TaskViewModel
	val taskListID by lazy { intent.getIntExtra(ARG_TASK_LIST_ID, 0) }

	val taskID by lazy { intent.getIntExtra(ARG_TASK_ID, 0) }
	val taskName by lazy { intent.getStringExtra(ARG_TASK_NAME).toString() }
	val taskDesc by lazy { intent.getStringExtra(ARG_TASK_DESCRIPTION).toString() }
	val taskIsFavourite by lazy { intent.getBooleanExtra(ARG_TASK_IS_FAVOURITE, false) }
	val createDate by lazy { intent.getStringExtra(ARG_TASK_CREATE_DATE) }
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityTaskBinding.inflate(layoutInflater)

		taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

		mode = intent.getStringExtra(ARG_TASK_MODE).toString()

		if (mode == "EDIT") {
			// Если в режиме редактирования, то кнопка добавления скрыта
			binding.add.visibility = View.INVISIBLE
			binding.deleteTask.visibility = View.VISIBLE
			binding.save.visibility = View.VISIBLE
			binding.createDateContent.visibility = View.VISIBLE
			binding.createDateText.visibility = View.VISIBLE
			// taskId = intent.getIntExtra("taskId", 0)

			binding.taskName.setText(taskName)
			binding.taskDesc.setText(taskDesc)
			binding.createDateContent.setText(createDate)

			binding.isFavouriteTask.isChecked = taskIsFavourite

		} else if (mode == "CREATE") {
			// Если в режиме редактирования, то кнопка сохранения скрыта
			binding.add.visibility = View.VISIBLE
			binding.deleteTask.visibility = View.INVISIBLE
			binding.save.visibility = View.INVISIBLE
			binding.createDateContent.visibility = View.GONE
			binding.createDateText.visibility = View.GONE

		}

		setContentView(binding.root)
		binding.add.setOnClickListener {
			val name = binding.taskName.text.toString()
			val desc = binding.taskDesc.text.toString()
			val isChecked = binding.isFavouriteTask.isChecked
			if (desc.isNotEmpty() && name.isNotEmpty()) {

				taskViewModel.addTask(Task(name, desc, taskListID, isChecked))

				finish()
			}
		}
		binding.save.setOnClickListener {
			val name = binding.taskName.text.toString()
			val desc = binding.taskDesc.text.toString()
			val isChecked = binding.isFavouriteTask.isChecked

			if (desc.isNotEmpty() && name.isNotEmpty()) {
				taskViewModel.updateTask(
					Task(name, desc, taskListID, isChecked, taskID)
				)
				finish()
			}
		}
		binding.deleteTask.setOnClickListener {
			taskViewModel.deleteTask(
				Task(taskName, taskDesc, taskListID, taskIsFavourite, taskID)
			)
			finish()
		}
	}

	companion object {
		private const val ARG_TASK_LIST_ID = "taskListID"
		private const val ARG_TASK_MODE = "mode"
		private const val ARG_TASK_ID = "taskId"
		private const val ARG_TASK_NAME = "taskName"
		private const val ARG_TASK_DESCRIPTION = "taskDesc"
		private const val ARG_TASK_IS_FAVOURITE = "taskIsFavourite"
		private const val ARG_TASK_CREATE_DATE = "createDate"
		//private val taskId:Int = 0


		fun getIntent(context: Context, taskListId: Int): Intent {
			val intent = Intent(context, TaskActivity::class.java)
			intent.putExtra(ARG_TASK_LIST_ID, taskListId)
			intent.putExtra(ARG_TASK_MODE, "CREATE")
			return intent
		}

		fun getIntent(context: Context, task: Task): Intent {
			val intent = Intent(context, TaskActivity::class.java)
			intent.putExtra(ARG_TASK_ID, task.id)
			intent.putExtra(ARG_TASK_NAME, task.name)
			intent.putExtra(ARG_TASK_DESCRIPTION, task.description)
			intent.putExtra(ARG_TASK_IS_FAVOURITE, task.isFavourite)
			intent.putExtra(ARG_TASK_LIST_ID, task.taskListId)
			intent.putExtra(ARG_TASK_MODE, "EDIT")
			intent.putExtra(ARG_TASK_CREATE_DATE, task.createDate)
			return intent
		}
	}
}