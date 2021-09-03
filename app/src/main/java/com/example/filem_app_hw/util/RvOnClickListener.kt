package com.example.filem_app_hw.util

import android.view.View
import androidx.databinding.ViewDataBinding

interface RvOnClickListener {
    fun onItmClick(binding: View, elementPositionID: Int? = null)
}