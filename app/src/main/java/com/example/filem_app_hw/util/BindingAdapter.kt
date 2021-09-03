package com.example.filem_app_hw.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.filem_app_hw.data.Genric
import com.example.filem_app_hw.util.Constants.IMAGE_BASE




@BindingAdapter(value = ["app:showWhenLoading"])
fun <T> showWhenLoading(view:View , status: Status<T>?){
    if (status is Status.Loading){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenError"])
fun <T> showWhenError(view:View , status: Status<T>?){
    if (status is Status.Error){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:showWhenSuccess"])
fun <T> showWhenSuccess(view:View , status: Status<T>?){
    if (status is Status.Success){
        view.visibility = View.VISIBLE
    }else{
        view.visibility = View.GONE
    }
}

@BindingAdapter(value = ["app:visibleRecycler"])
fun <T> visibleRecycler(view:View , status: Status<T>?){
    if (status is Status.Success){
        view.visibility = View.GONE
    }else{
        view.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = ["app:imgUrl"])
fun setImageFromUrl(
    view: ImageView , url: String?){
    Glide.with(view)
        .load(IMAGE_BASE+url).centerCrop().into(view)
}


@BindingAdapter(value = ["app:genericList"])
fun setGenricMoview(
    view: TextView , genericList: List<Genric>?){
    var items = " "
    genericList?.forEach {
         items+=it.name + " ,"
   }
    view.text = items.removeRange(items.length-1 , items.length)
}


