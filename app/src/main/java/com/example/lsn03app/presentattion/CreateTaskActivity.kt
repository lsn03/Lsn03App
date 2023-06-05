package com.example.lsn03app.presentattion

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lsn03app.R
import com.example.lsn03app.databinding.ActivityTaskBinding
import com.example.lsn03app.di.Dependencies
import com.example.lsn03app.domain.models.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.Int as Int

class CreateTaskActivity : AppCompatActivity() {

	lateinit var binding: ActivityTaskBinding
	private lateinit var mode: String

	val taskListID by lazy { intent.getIntExtra(ARG_TASK_LIST_ID, 0)}

	val taskID by lazy { intent.getIntExtra(ARG_TASK_ID, 0)}
	val taskName by lazy { intent.getStringExtra(ARG_TASK_NAME).toString() }
	val taskDesc by lazy { intent.getStringExtra(ARG_TASK_DESCRIPTION).toString() }
	val taskIsFavourite by lazy { intent.getBooleanExtra(ARG_TASK_IS_FAVOURITE,false) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityTaskBinding.inflate(layoutInflater)


		mode = intent.getStringExtra(ARG_TASK_MODE).toString()




			// Если в режиме редактирования, то кнопка сохранения скрыта
			binding.add.visibility = View.VISIBLE
			binding.deleteTask.visibility= View.INVISIBLE
			binding.save.visibility = View.INVISIBLE

			binding.add.text = getString(R.string.add)


		setContentView(binding.root)
		binding.add.setOnClickListener {
			val title = binding.title.text.toString()
			val desc = binding.desc.text.toString()
			if (desc.isNotEmpty() && title.isNotEmpty()) {
				GlobalScope.launch {
					Dependencies.taskRepository.addTask(Task(title, desc, taskListID))
				}
				finish()
			}
		}
		binding.save.setOnClickListener {
			val title = binding.title.text.toString()
			val desc = binding.desc.text.toString()
			if (desc.isNotEmpty() && title.isNotEmpty()) {
				GlobalScope.launch {
					Dependencies.taskRepository.updateTask(Task(title, desc,taskID, taskListID))
				}
				finish()
			}
		}
		binding.deleteTask.setOnClickListener {
			GlobalScope.launch {
				Dependencies.taskRepository.deleteTask(Task(taskName, taskDesc,taskID, taskListID))
			}
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

		//private val taskId:Int = 0



		fun getIntent(context: Context, taskListId: Int) : Intent{
			val intent = Intent(context, CreateTaskActivity::class.java)
			intent.putExtra(ARG_TASK_LIST_ID, taskListId)
			intent.putExtra(ARG_TASK_MODE, "CREATE")
			return intent
		}
		fun getIntent(context: Context,task:Task) : Intent{
			val intent = Intent(context, CreateTaskActivity::class.java)
			intent.putExtra(ARG_TASK_ID,task.id)
			intent.putExtra(ARG_TASK_NAME,task.id)
			intent.putExtra(ARG_TASK_DESCRIPTION,task.id)
			intent.putExtra(ARG_TASK_IS_FAVOURITE,task.id)

			intent.putExtra(ARG_TASK_MODE, "EDIT")

			return intent
		}
	}
}