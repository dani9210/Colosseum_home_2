package com.example.colosseum_home.datas

import org.json.JSONObject

class SideData {

    var id = 0
    var title = ""
    var voteCount = 0

    companion object{

        fun getSideDataFromJSON(jsonObj : JSONObject) : SideData {

            val sideData = SideData()

            sideData.id = jsonObj.getInt("id")
            sideData.title = jsonObj.getString("title")
            sideData.voteCount = jsonObj.getInt("vote_count")

            return sideData

        }


    }

}