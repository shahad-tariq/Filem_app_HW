package com.example.filem_app_hw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.filem_app_hw.databinding.FragmentSoonBinding
import com.example.filem_app_hw.viewModel.MainViewModel


class SoonFragment : Fragment() {

    lateinit var binding : FragmentSoonBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSoonBinding.inflate(layoutInflater)
        binding.soonFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}