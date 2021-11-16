package com.example.colosseum_home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.example.colosseum_home.databinding.ActivitySignUpBinding
import com.example.colosseum_home.utils.ServerUtil
import org.json.JSONObject
import java.util.*

class SignUpActivity : BaseActivity() {


    lateinit var binding: ActivitySignUpBinding

//    이메일 중복검사 통과 여부 저장 변수.

    var isEmilOk = false  // 기본값 :  통과 X , 그래서 false. => 자료형 자동으로 Boolean 설정정

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)

        setupEvents()
        setValues()


    }


    override fun setupEvents() {

        binding.emailEdt.addTextChangedListener {

//            val inputContent = it.toString()
//            Log.d("변경된내용",inputContent)

//            이메일이 한글자라도 바뀌면 -> 검사를 다시 요구.

            binding.emailCheckResultTxt.text = "이메일 중복검사를 해주세요"
            isEmilOk = false

        }

        binding.checkEmailBtn.setOnClickListener {

            val inputEmail = binding.emailEdt.text.toString()

            ServerUtil.getRequestDuplCheck("EMAIL",inputEmail, object : ServerUtil.JsonResponseHandler {
                override fun onResponse(jsonObj: JSONObject) {

                    val code = jsonObj.getInt("code")

                    runOnUiThread {

                        if (code == 200) {

                            binding.emailCheckResultTxt.text = "사용해도 좋습니다"
                            isEmilOk = true

                        } else {

                            binding.emailCheckResultTxt.text = "사용중인 이메일 입니다"
                            isEmilOk = false


                        }
                    }





                }

            })

        }


        binding.okBtn.setOnClickListener {



            val inputEmail = binding.emailEdt.text.toString()
            val inputPw = binding.pwEdt.text.toString()
            val inputNickname = binding.nicknameEdt.text.toString()


//            입력값들이 괜찮은지 먼저 검사. => 전부 통과해야 회원가입 실행.
            if(!isEmilOk){

                Toast.makeText(mContext, "이메일 확인을 다시 해주세요.", Toast.LENGTH_SHORT).show()

//                 return : 원래는 함수의 결과를 지정하기 위한 용도.
//                 응용 : 결과 뒤로의 함수 내용은 실행되지 않는다. => 이 함수를 강제 종료 시키는 기능.

                return@setOnClickListener
            }

//            입력한 비번이 8글자 이상인가? 검증

            if (inputPw.length < 8){
                Toast.makeText(mContext, "비밀번호는 8글자 이상으로 해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
//            입력한 데이터를 => 서버의 회원가입 기능에 요청  => ServerUtil 함수 활용. => 함수가 아직없으니 추가로 만들자

            ServerUtil.putRequestSignUp(inputEmail, inputPw, inputNickname, object : ServerUtil.JsonResponseHandler {
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