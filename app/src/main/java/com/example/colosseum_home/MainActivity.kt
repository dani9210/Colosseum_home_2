package com.example.colosseum_home


import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivityMainBinding
import com.example.colosseum_home.utils.ServerUtil

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

        ServerUtil.getRequestMyInfo(mContext,null)


    }

}