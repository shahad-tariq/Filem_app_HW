package com.example.filem_app_hw.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.filem_app_hw.R
import com.example.filem_app_hw.databinding.FragmentDetailsBinding
import com.example.filem_app_hw.databinding.FragmentFavoriteBinding
import com.example.filem_app_hw.util.Constants.MOVIE_ID
import com.example.filem_app_hw.viewModel.MainViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.observeOn
import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

    lateinit var binding : FragmentDetailsBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        binding.detailsFragModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        BottomSheetBehavior.from(binding.layoutRecycler).apply {
            peekHeight = 560
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        val arg = Bundle(arguments)
        var movieId = arg.getInt(MOVIE_ID)

        viewModel.apply {
            getDetails(movieId)
            getSimilar(movieId)
        }

    }


}