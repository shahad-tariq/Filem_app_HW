package com.example.filem_app_hw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.filem_app_hw.BR
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_BIG_CARD
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_SMALL_CARD
import com.example.filem_app_hw.util.RvOnClickListener
import com.example.filem_app_hw.R
import com.example.filem_app_hw.data.Result
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_BIG_X_CARD
import com.example.filem_app_hw.util.Constants.VIEW_TYPE_VERY_SMALL_CARD


class NestedAdapter<T>(private val parentPosition: Int) : RecyclerView.Adapter<NestedAdapter.NestedHolder>() {

    private var dataList:List<Any>? = emptyList<Any>()
    private lateinit var onClickListenerNested: RvOnClickListener

    private var layoutId: Int? = null

    fun setData(dataList: List<Any>?, typeDisplay: Int) {
        this.dataList = dataList
        getLayoutId(typeDisplay)
        notifyDataSetChanged()
    }

    fun getLayoutId (typeDisplay:Int){
        layoutId =  when(typeDisplay){
                            VIEW_TYPE_VERY_SMALL_CARD -> R.layout.item_nested_holder_very_small
                            VIEW_TYPE_SMALL_CARD ->  R.layout.items_nested_small_holder
                            VIEW_TYPE_BIG_CARD -> R.layout.item_nested_holder
                            VIEW_TYPE_BIG_X_CARD ->  R.layout.item_nested_holder_big_x
                            else -> null
                     }
    }

    fun setOnClickListenerNested(onClickListenerNested: RvOnClickListener) {
        this.onClickListenerNested = onClickListenerNested
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NestedHolder {
        return NestedHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                layoutId!!, parent,false) , onClickListenerNested  , parentPosition)
    }

    override fun getItemCount(): Int {
        return if(dataList.isNullOrEmpty()) 0 else dataList!!.size
    }

    override fun onBindViewHolder(holder: NestedHolder, position: Int) {
        holder.bind(dataList!![position]  )
    }

    class NestedHolder(private val binding: ViewDataBinding,
                       private val onClickListener: RvOnClickListener, private val parentPosition: Int) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        private var elementPositionID: Int = 0

        fun bind(data: Any ) {
            this.elementPositionID = (data as Result).id!!
            binding.setVariable(BR.itemNested, data)
            binding.executePendingBindings()
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onClickListener.onItmClick(v!! , elementPositionID)
        }
    }

}