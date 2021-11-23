package com.example.colosseum_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.colosseum_home.databinding.ActivityViewTopicDetailBinding
import com.example.colosseum_home.datas.TopicData
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailBinding

    lateinit var mTopicData : TopicData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil. setContentView(this,R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()

    }


    override fun setupEvents() {

    }

    override fun setValues() {

        mTopicData = intent.getSerializableExtra("topic") as TopicData
        binding.topicTitleTxt.text = mTopicData.title
        Glide.with(mContext).load(mTopicData.imageURL).into(binding.topicImg)
        getTopicDetailFromServer()

    }


    fun getTopicDetailFromServer ()  {

        ServerUtil.getRequestTopicDetail(mContext,mTopicData.id,"NEW",object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

            }


        })


    }


}