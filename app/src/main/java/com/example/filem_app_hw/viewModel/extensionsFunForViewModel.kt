package com.example.filem_app_hw

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.example.filem_app_hw.adapter.ParentAdapter
import com.example.filem_app_hw.repository.Repository
import com.example.filem_app_hw.util.Constants
import com.example.filem_app_hw.util.HasType
import com.example.filem_app_hw.util.RvOnClickListener
import com.example.filem_app_hw.util.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect




fun ParentAdapter.onClickAction() {

    this.setOnClickListener(object : RvOnClickListener {
        override fun onItmClick(view: View, elementPositionID: Int?) {
             Log.i("POSITION", "parent position - $view, nested position -  $elementPositionID")
             Navigation.findNavController(view).navigate(R.id.detailsFragment , Bundle().apply {
                 putInt(Constants.MOVIE_ID , elementPositionID!!)
             })
        }
    })

}

fun MutableList<HasType>.addToList(element: HasType) {
    this.add(element)
}

suspend fun <T> Repository.Collect(
    repoValue: Flow<Status<T?>>, liveValue: MutableLiveData<Status<T?>>) {
    repoValue
        .catch { Log.i(Constants.ERROR, "Error Repo") }
        .collect { liveValue.value = it }
}
