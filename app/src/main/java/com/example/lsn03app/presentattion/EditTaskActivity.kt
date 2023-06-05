package com.example.lsn03app.presentattion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lsn03app.R
import com.example.lsn03app.databinding.ActivityEditTaskBinding

class EditTaskActivity : AppCompatActivity() {
	lateinit var binding:ActivityEditTaskBinding
	val taskListID by lazy { intent.getIntExtra(CreateTaskActivity.ARG_TASK_LIST_ID, 0)}

	val taskID by lazy { intent.getIntExtra(CreateTaskActivity.ARG_TASK_ID, 0)}
	val taskName by lazy { intent.getStringExtra(CreateTaskActivity.ARG_TASK_NAME).toString() }
	val taskDesc by lazy { intent.getStringExtra(CreateTaskActivity.ARG_TASK_DESCRIPTION).toString() }
	val taskIsFavourite by lazy { intent.getBooleanExtra(CreateTaskActivity.ARG_TASK_IS_FAVOURITE,false) }

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityEditTaskBinding.inflate(layoutInflater)

		binding.save.visibility = View.VISIBLE
		binding.save.text = getString(R.string.save)

		binding.taskNameView.text = taskName
		binding.taskIdView.text = taskDesc

		setContentView(binding.root)
	}
}