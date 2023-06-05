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
enum class TaskMode {
	CREATE,
	EDIT
}
class TaskActivity : AppCompatActivity() {

	lateinit var binding: ActivityTaskBinding
	private lateinit var mode: String

	val taskListID by lazy { intent.getIntExtra(ARG_TASK_LIST_ID, 0)}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityTaskBinding.inflate(layoutInflater)
		setContentView(binding.root)

		mode = intent.getStringExtra(ARG_TASK_MODE).toString()

		if (mode == "EDIT") {
			// В режиме просмотра задачи скройте кнопку добавления и измените текст кнопки сохранения
			binding.add.visibility = View.GONE
			binding.save.text = getString(R.string.save)
		} else if (mode == "CREATE"){
			// В режиме создания задачи скройте кнопку сохранения и измените текст кнопки добавления
			binding.save.visibility = View.GONE
			binding.add.text = getString(R.string.add)
		}

		binding.add.setOnClickListener {
			val title = binding.title.text.toString()
			val desc = binding.desc.text.toString()
			GlobalScope.launch {
				Dependencies.taskRepository.addTask(Task(title, desc, taskListID))
			}
			finish()
		}

	}

	companion object {
		private const val ARG_TASK_LIST_ID = "taskListID"
		private const val ARG_TASK_MODE = "mode"
		private const val ARG_TASK_ID = "taskId"
		fun getIntent(context: Context, taskListId: Int) : Intent{
			val intent = Intent(context, TaskActivity::class.java)
			intent.putExtra(ARG_TASK_LIST_ID, taskListId)
			intent.putExtra(ARG_TASK_MODE, "CREATE")
			return intent
		}
		fun getIntent(context: Context,task:Task, taskListId: Int) : Intent{
			val intent = Intent(context, TaskActivity::class.java)
			intent.putExtra(ARG_TASK_LIST_ID, taskListId)
			intent.putExtra(ARG_TASK_ID,task.id)
			intent.putExtra(ARG_TASK_MODE, "EDIT")

			return intent
		}
	}
}