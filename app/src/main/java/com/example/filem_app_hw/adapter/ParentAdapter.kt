package com.example.filem_app_hw.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.filem_app_hw.BR
import com.example.filem_app_hw.R
import com.example.filem_app_hw.data.dataAdapter.Categories
import com.example.filem_app_hw.data.dataAdapter.DataObjectWrapper
import com.example.filem_app_hw.data.dataAdapter.NestedDataObjectWrapper
import com.example.filem_app_hw.data.dataAdapter.NestedDataObjectWrapperVertical
import com.example.filem_app_hw.util.HasType
import com.example.filem_app_hw.util.Holder
import com.example.filem_app_hw.util.RvOnClickListener

class ParentAdapter : RecyclerView.Adapter<ParentAdapter.ViewHolder>() {

    private var listOfData = listOf<HasType>()
    private lateinit var onClickListenerNested: RvOnClickListener

    private var layoutId: Int? = null

    fun setData(dataList: List<HasType>) {
        listOfData = emptyList()
        listOfData = dataList
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClickListenerNested: RvOnClickListener) {
        this.onClickListenerNested = onClickListenerNested
    }

    fun getLayoutId (viewType:Int){
        layoutId =
            when (viewType) {
                Holder.PARENT_Object.type ->   R.layout.item_parent_object_holder
                Holder.PARENT_CAtegories.type ->   R.layout.item_parent_holder
                Holder.NESTED_Horizantal.type -> R.layout.item_nested_horizontal_host
                Holder.NESTED_Vertical.type -> R.layout.item_nested_vertical_host
                else -> null
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        getLayoutId(viewType)
        return ViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context),
                layoutId!!, parent, false), onClickListenerNested)
    }

    override fun getItemCount(): Int {
        return listOfData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfData[position], getItemViewType(position), position)
    }

    override fun getItemViewType(position: Int): Int {
        return listOfData[position].getType()
    }



    class ViewHolder(private val binding: ViewDataBinding, private val onClickListener: RvOnClickListener)
        : RecyclerView.ViewHolder(binding.root)
        , View.OnClickListener {

        private var parentPosition: Int = 0

        fun bind(dataObject: HasType, viewType: Int, parentPosition: Int) {
            this.parentPosition = parentPosition

            when (viewType) {
                Holder.PARENT_Object.type -> {
                    binding.setDataForType(BR.dataItem, dataObject as DataObjectWrapper)
                }
                Holder.PARENT_CAtegories.type -> {
                    binding.setDataForType(BR.dataItem, dataObject as Categories)
                }
                Holder.NESTED_Horizantal.type -> {
                    val nestedAdapter = NestedAdapter<Any>(parentPosition).setNestedAdapter(
                        (dataObject as NestedDataObjectWrapper<Any>).nestedDataObjectList, dataObject.typeDisplay)

                    binding.setDataForType(BR.adapterHorizantal, nestedAdapter)
                }
                Holder.NESTED_Vertical.type -> {
                    val nestedAdapter =  NestedAdapter<Any>(parentPosition).setNestedAdapter(
                        (dataObject as NestedDataObjectWrapperVertical<Any>).nestedDataObjectList,
                        dataObject.typeDisplay)

                    binding.setDataForType(BR.adapterVertical, nestedAdapter)
                }
            }

        }

        fun <T> NestedAdapter<T>.setNestedAdapter(nestedDataObjectList: List<Any>?,
                                                  typeDisplay: Int): NestedAdapter<T> {
            return this.apply {
                setOnClickListenerNested(onClickListener)
                setData(nestedDataObjectList, typeDisplay)
            }
        }

        fun ViewDataBinding.setDataForType(ValueBR: Int, Adapter: Any) {
            setVariable(ValueBR, Adapter)
            executePendingBindings()
            root.setOnClickListener(this@ViewHolder)
        }

        override fun onClick(v: View?) {
            onClickListener.onItmClick(v!!)
        }

    }
}







