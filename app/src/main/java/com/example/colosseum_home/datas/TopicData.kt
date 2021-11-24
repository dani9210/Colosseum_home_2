package com.example.colosseum_home.datas

import org.json.JSONObject
import java.io.Serializable

class TopicData  (
    var id : Int,
    var title : String,
    var imageURL : String) : Serializable{

//    의견 (댓글) 갯수 파싱

    var replyCount = 0

//    선택 진영 "목록"

    val sideList = ArrayList<SideData>()


//     토픽데이터 만들때는 빈 괄호 TopicData() 형태도 지원하자.
//    다른 형태의 생성자 (보조생성자) 도 추가지원.

    constructor() :  this ( 0 ,"제목없음","주소없음" )

    companion object{

//        매번 파싱하는 코드는 화면마다 짜기 번거로움
//        TopicData의 기능으로 ->  토론에대한 내용을 갖고잇는 jsonObj를 넣으면
//           =>파싱을 진행해서 ->TopicData 형태로 변환해주는 기능.

//        다른화면에서는 jsonObj만 파싱해서 => 변환기능으로 활용만.

        fun getTopicDataFromJSON(jsonObj : JSONObject ) : TopicData{

            val topicData = TopicData()

            topicData.id = jsonObj.getInt("id")
            topicData.title = jsonObj.getString("title")
            topicData.imageURL = jsonObj.getString("img_url")
            topicData.replyCount = jsonObj.getInt("reply_count")

            return topicData

        }

    }
}