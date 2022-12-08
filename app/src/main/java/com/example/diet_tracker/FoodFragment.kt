package com.example.diet_tracker

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.diet_tracker.databinding.FragmentFoodBinding
import com.example.diet_tracker.databinding.FragmentFoodBinding.inflate

class FoodFragment : Fragment() {

    companion object {
        fun newInstance() = LogFragment()
    }


    private lateinit var viewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = inflater.inflate(R.layout.fragment_log, container, false)
        return fragmentBinding.rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodViewModel::class.java)
        // TODO: Use the ViewModel

    }



}