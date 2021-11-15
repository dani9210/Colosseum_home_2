package com.example.colosseum_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivityMainBinding
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



        binding.loginBtn.setOnClickListener {

            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

            Log.d("입력이메일", inputEmail)
            Log.d("입력비밀번호", inputPw)

//            서버의 로그인 기능에 전달.

            ServerUtil.postRequestLogin(inputEmail, inputPw, object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

//                    화면단에서 jsonObj 분석- > 상황에맞는 UI처리

                        val code = jsonObj.getInt("code")
//                    로그인 성공시 - > 성공
//                   실패시 -> 왜 실패했는지 서버가 알려주는대로 토스트

                        runOnUiThread {


                            if (code == 200) {


                                Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT)
                                    .show()

                            } else {

                                val message = jsonObj.getString("message")

                                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT)
                                    .show()


                            }


                        }


                    }


                })


        }


    }
}