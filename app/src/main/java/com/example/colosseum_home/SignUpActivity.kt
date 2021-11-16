package com.example.colosseum_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivitySignUpBinding
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject

class SignUpActivity : BaseActivity() {


    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setupEvents()
        setValues()


    }


    override fun setupEvents() {

        binding.checkEmailBtn.setOnClickListener {

            val inputEmail = binding.emailEdt.text.toString()

            ServerUtil.getRequestDuplCheck("EMAIL",inputEmail,null)

        }


        binding.okBtn.setOnClickListener {

            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.pwEdt.text.toString()
            val inputNickname = binding.nicknameEdt.text.toString()

//            입력한 데이터를 => 서버의 회원가입 기능에 요청  => ServerUtil 함수 활용. => 함수가 아직없으니 추가로 만들자

            ServerUtil.putRequestSignUp(
                inputEmail,
                inputPw,
                inputNickname,
                object : ServerUtil.JsonResponseHandler {
                    override fun onResponse(jsonObj: JSONObject) {

//                     jsonObj 분석 -> UI반영 코드만 작성.

                        val code = jsonObj.getInt("code")
                        if (code == 200) {

//                        회원가입 성공 => --님 회원가입을 축하합니다

                            val dataObj = jsonObj.getJSONObject("data")
                            val userObj = dataObj.getJSONObject("user")
                            val nickname = userObj.getString("nick_name")

                            runOnUiThread {


                                Toast.makeText(mContext, "${nickname}님 회원가입을 축하합니다", Toast.LENGTH_SHORT).show()

//                            회원가입 종료 ., 로그인으로 복귀

                                finish()

                            }


                        } else {

                            val message = jsonObj.getString("message")

                            runOnUiThread {

                                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show()

                            }


                        }


                    }


                })


        }

    }

    override fun setValues() {
    }


}