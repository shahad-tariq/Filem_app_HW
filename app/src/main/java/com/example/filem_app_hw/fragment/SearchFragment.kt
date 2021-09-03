package com.example.filem_app_hw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.filem_app_hw.R
import com.example.filem_app_hw.databinding.FragmentFavoriteBinding
import com.example.filem_app_hw.databinding.FragmentSearchBinding
import com.example.filem_app_hw.viewModel.MainViewModel

class SearchFragment : Fragment() {

    lateinit var binding : FragmentSearchBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater)
        binding.searchFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}