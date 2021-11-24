package com.example.colosseum_home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.colosseum_home.adapters.ReplyAdapter
import com.example.colosseum_home.databinding.ActivityViewTopicDetailBinding
import com.example.colosseum_home.datas.ReplyData
import com.example.colosseum_home.datas.TopicData
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class ViewTopicDetailActivity : BaseActivity() {

    lateinit var binding : ActivityViewTopicDetailBinding

    lateinit var mTopicData : TopicData

    val mReplyList = ArrayList<ReplyData>()

    lateinit var mReplyAdapter: ReplyAdapter

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
        binding.replyCountTxt.text = "현재 의견 : ${mTopicData.replyCount}개"

        mReplyAdapter = ReplyAdapter(mContext, R.layout.reply_list_item,mReplyList)
        binding.replyListView.adapter = mReplyAdapter

    }


    fun getTopicDetailFromServer ()  {

        ServerUtil.getRequestTopicDetail(mContext,mTopicData.id,"NEW",object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

//                댓글 목록 JSONArray -> 파싱 -> mReplyList의 자료로 추가

                val dataObj  = jsonObj.getJSONObject("data")
                val topicObj = dataObj.getJSONObject("topic")

//                topicObj (JSONObject)를 새 TopicData로 파싱 => 최신정보 반영

                mTopicData = TopicData.getTopicDataFromJSON(topicObj)

                val repliesArr = topicObj.getJSONArray("replies")

                for( i in 0 until repliesArr.length()) {

                    val replyObj = repliesArr.getJSONObject(i)

//                    JSONObject -> ReplyData 객체로 벼환.

                    val replyData = ReplyData()
                    replyData.id = replyObj.getInt("id")
                    replyData.content = replyObj.getString("content")

//                    댓글 목록으로 추가

                    mReplyList.add(replyData)

                }

//                리스트뷰의 목록에 변경  => 리스트뷰 어댑터 새로고침 처리

                runOnUiThread {

                    mReplyAdapter.notifyDataSetChanged()

                }









            }


        })


    }


}