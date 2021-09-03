package com.example.filem_app_hw.data.dataAdapter

import com.example.filem_app_hw.data.LatestResponse
import com.example.filem_app_hw.util.HasType
import com.example.filem_app_hw.util.Holder

class DataObjectWrapper (val items: LatestResponse? ) : HasType {

    override fun getType(): Int {
        return Holder.PARENT_Object.type;
    }
}