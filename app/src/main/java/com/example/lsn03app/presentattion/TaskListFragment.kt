package com.example.lsn03app.presentattion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lsn03app.databinding.FragmentTaskListBinding




class TaskListFragment(private val taskListId:Int) : Fragment() {
	private lateinit var viewModel: TaskListViewModel
	private lateinit var binding: FragmentTaskListBinding



	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentTaskListBinding.inflate(layoutInflater)
		viewModel = ViewModelProvider(this)[TaskListViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val adapter = TaskListAdapter()

		binding.rv.adapter = adapter
		binding.rv.layoutManager = LinearLayoutManager(requireActivity())
		viewModel.list.observe(viewLifecycleOwner){
			adapter.submitList(it)
		}

		viewModel.getTasksFromTaskList(taskListId)
	}


}