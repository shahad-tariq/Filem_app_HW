package com.example.filem_app_hw.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.filem_app_hw.R
import com.example.filem_app_hw.viewModel.MainViewModel
import com.example.filem_app_hw.databinding.FragmentMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior


class MainFragment : Fragment() {

    lateinit var binding : FragmentMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.mainFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BottomSheetBehavior.from(binding.layoutRecycler).apply {
            peekHeight = 1100
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        binding.searchAction.setOnClickListener { view ->
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_searchFragment)
        }
    }

}