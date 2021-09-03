package com.example.filem_app_hw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.filem_app_hw.databinding.FragmentFavoriteBinding
import com.example.filem_app_hw.viewModel.MainViewModel


class FavoriteFragment : Fragment() {

    lateinit var binding : FragmentFavoriteBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        binding.favoriteFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}