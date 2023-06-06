package com.example.lsn03app.presentattion

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lsn03app.domain.models.TaskList

class ViewPagerAdapter(fragmentActivity: FragmentActivity,private val taskList:List<TaskList>) :FragmentStateAdapter(fragmentActivity) {

	override fun getItemCount(): Int {
		return taskList.count()
	}

	override fun createFragment(position: Int): Fragment {
		return TaskListFragment(taskList[position].id)
	}

}