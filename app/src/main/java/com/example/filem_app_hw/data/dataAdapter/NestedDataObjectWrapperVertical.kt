package com.example.filem_app_hw.data.dataAdapter

import com.example.filem_app_hw.data.Result
import com.example.filem_app_hw.util.HasType
import com.example.filem_app_hw.util.Holder

class NestedDataObjectWrapperVertical <T> (val nestedDataObjectList: List<T>?, val typeDisplay: Int) : HasType {

    override fun getType(): Int {
        return Holder.NESTED_Vertical.type;
    }
}