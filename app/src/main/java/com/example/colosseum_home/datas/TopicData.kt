package com.example.colosseum_home.datas

import java.io.Serializable

class TopicData  (
    var id : Int,
    var title : String,
    var imageURL : String) : Serializable{

//     토픽데이터 만들때는 빈 괄호 TopicData() 형태도 지원하자.
//    다른 형태의 생성자 (보조생성자) 도 추가지원.

    constructor() :  this ( 0 ,"제목없음","주소없음" )

    companion object{

//        매번 파싱하는 코드는 화면마다 짜기 번거로움
//        TopicData의 기능으로 ->  토론에대한 내용을 갖고잇는 jsonObj를 넣으면
//           =>파싱을 진행해서 ->TopicData

    }
}