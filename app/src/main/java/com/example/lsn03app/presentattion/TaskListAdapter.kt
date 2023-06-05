package com.example.lsn03app.presentattion

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lsn03app.R
import com.example.lsn03app.domain.models.Task

class TaskListAdapter : ListAdapter<Task, TaskListAdapter.MyViewHolder>(MyDiffUtil()) {


	class MyViewHolder(view: View) : ViewHolder(view) {
		val name = view.findViewById<TextView>(R.id.name)

	}

	class MyDiffUtil : DiffUtil.ItemCallback<Task>() {
		override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
			return oldItem.id == newItem.id
		}

		override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
			return oldItem == newItem
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
		return MyViewHolder(view)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		//holder.name.text = currentList[position].name

		val task = currentList[position]
		holder.name.text = task.name

		holder.itemView.setOnClickListener {
			openTaskDetails(task,position)
		}
	}
	private fun openTaskDetails(task: Task,taskListId:Int) {


	}
}