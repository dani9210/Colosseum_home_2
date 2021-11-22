package com.example.colosseum_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivityViewTopicDetailBinding

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = DataBindingUtil. setContentView(this,R.layout.activity_view_topic_detail)
        setupEvents()
        setValues()

    }


    override fun setupEvents() {

    }

    override fun setValues() {

    }


}