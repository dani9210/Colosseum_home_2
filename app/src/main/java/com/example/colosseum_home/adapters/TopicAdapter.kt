package com.example.colosseum_home.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.example.colosseum_home.datas.TopicData

class TopicAdapter(
    val mContext : Context,
    resId : Int ,
    val mList : List<TopicData>) : ArrayAdapter<TopicData>(mContext,resId,mList) {
}