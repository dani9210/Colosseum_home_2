package com.example.colosseum_home


import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.adapters.TopicAdapter
import com.example.colosseum_home.databinding.ActivityMainBinding
import com.example.colosseum_home.datas.TopicData
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity(){

    lateinit var binding : ActivityMainBinding
    lateinit var mTopicAdapter : TopicAdapter

    val mTopicList = ArrayList<TopicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }



    override fun setupEvents() {

    }

    override fun setValues() {

//        /v2/main_info API가 토론 주제 목록을 내려줌.
//        서버 호출 =>  파싱해서, mTopicList를 채워주자.
        getTopicListFromServer()

        mTopicAdapter = TopicAdapter(mContext,R.layout.topic_list_item,mTopicList)
        binding.topicListView.adapter = mTopicAdapter

    }



    fun getTopicListFromServer() {

        ServerUtil.getRequestMainInfo(mContext,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val topicsArr = dataObj.getJSONArray("topics")

//                0번째 주제부터 ~topicsArr 갯수 직전까지 반복
//                5개주제  : 0~4번 주제까지. (5개)


                for ( index in 0 until topicsArr.length()){

//                   [  ]  안에있는 {  }를 순서대로 찾아내서 파싱하자.

                    val topicObj = topicsArr.getJSONObject(index)

//                    topicObj는 토론 주제에 필요한 데이터를 들고잇다.
//                    TopicData() 형태로 변환해주자 => 목록에 추가해주자.

                    val topicData = TopicData()
                    topicData.id = topicObj.getInt("id")
                    topicData.title = topicObj.getString("title")
                    topicData.imageURL = topicObj.getString("img_url")

//                    만들어진 토픽 데이터를 목록에 추가해준다

                    mTopicList.add(TopicData())



                }

//                for문이 끝나면, mTopiclist에 모든 토론 주제가 추가된상태다.




            }


        })


    }

}