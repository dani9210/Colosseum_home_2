package com.example.colosseum_home.datas

class TopicData (
    var id : Int,
    var title : String,
    var imageURL : String) {

//     토픽데이터 만들때는 빈 괄호 TopicData() 형태도 지원하자.
//    다른 형태의 생성자 (보조생성자) 도 추가지원.

    constructor() :  this ( 0 ,"제목없음","주소없음" )
}