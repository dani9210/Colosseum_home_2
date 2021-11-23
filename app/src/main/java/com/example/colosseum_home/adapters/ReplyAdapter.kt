package com.example.colosseum_home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.colosseum_home.R
import com.example.colosseum_home.datas.ReplyData
import com.example.colosseum_home.datas.TopicData

class ReplyAdapter(
    val mContext : Context,
    resId : Int ,
    val mList : List<ReplyData>) : ArrayAdapter<ReplyData>(mContext,resId,mList) {


        val  mInflater = LayoutInflater.from(mContext)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if(tempRow == null){

            tempRow = mInflater.inflate(R.layout.activity_view_topic_detail,null)

        }

        val row = tempRow!!


        val data = mList[position]



        return row

    }
}