package com.example.colosseum_home

import android.content.Intent
import android.media.audiofx.BassBoost
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivityMainBinding
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class MainActivity : BaseActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()


    }


    override fun setupEvents() {


        binding.singUpBtn.setOnClickListener {

            val myIntent = Intent(mContext,SignUpActivity::class.java)

            startActivity(myIntent)



        }

        binding.loginBtn.setOnClickListener {

            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.passwordEdt.text.toString()

            Log.d("입력이메일", inputEmail)
            Log.d("입력비밀번호", inputPw)

//            서버의 로그인 기능에 전달.

            ServerUtil.postRequestLogin(
                inputEmail,
                inputPw,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

//                    화면단에서 jsonObj 분석- > 상황에맞는 UI처리

                        val code = jsonObj.getInt("code")
//                    로그인 성공시 - > 성공
//                   실패시 -> 왜 실패했는지 서버가 알려주는대로 토스트

                        runOnUiThread {

//                            안드로이드는 UI 전담 쓰레드 외의 다른 쓰레드가 UI를 조작하면, 위험요소로 간주하고 앱을 죽임.
//                            그래서 꼭  runOnUiThread 안에 작업해주어야함


                            if (code == 200) {

                                val dataObj = jsonObj.getJSONObject("data")
                                val userObj = dataObj.getJSONObject("user")
                                val nickname = userObj.getString("nick_name")


                                Toast.makeText(mContext, "${nickname}님 환영합니다!", Toast.LENGTH_SHORT)
                                    .show()

                            } else {

                                val message = jsonObj.getString("message")

                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT)
                                    .show()


                            }


                        }


                    }


                })


        }

    }

    override fun setValues() {

    }


}