package com.example.filem_app_hw.data.dataAdapter

import com.example.filem_app_hw.util.HasType
import com.example.filem_app_hw.util.Holder

class Categories(val value: String ) : HasType {

    override fun getType(): Int {
        return Holder.PARENT_CAtegories.type;
    }
}