package com.example.colosseum_home


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivityMainBinding
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity(){

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setupEvents()
        setValues()
    }



    override fun setupEvents() {

    }

    override fun setValues() {

//        연습 - 내 정보를 받아오기 호출 =>  닉네임 파싱, 텍스트뷰에 반영영

        ServerUtil.getRequestMyInfo(mContext,object : ServerUtil.JsonResponseHandler{
            override fun onResponse(jsonObj: JSONObject) {

                val dataObj = jsonObj.getJSONObject("data")
                val userObj = dataObj.getJSONObject("user")
                val nickname = userObj.getString("nick_name")


                runOnUiThread {

                    binding.nicknameTxt.text = nickname

                }



            }
        })


    }

}