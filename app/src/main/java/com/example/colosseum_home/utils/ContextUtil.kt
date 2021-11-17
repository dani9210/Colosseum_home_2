package com.example.colosseum_home.utils

import android.content.Context

class ContextUtil {

    companion object{

//        외부 노출안시키려고 private를  val 앞에 넣는다  (다른 코틀린단에서 보이지 않게)
//         오타내기싫어서 미리 변수에담아서 오타확률을 줄인다


        private val prefName = "ColosseumPref"

        private val TOKEN = "TOKEN"

//             자동로그인 체크여부 저장

        private val AUTO_LOGIN = "AUTO_LOGIN"

//        자동로그인  setter/ getter 작성

        fun setAutoLogin(context: Context,isAuto : Boolean){

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)

            pref.edit().putBoolean(AUTO_LOGIN,isAuto).apply()

        }

        fun getAutoLogin(context: Context) : Boolean{
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
            return pref.getBoolean(AUTO_LOGIN, false)

        }




            //  setter - 토큰 저장 기능.  SAVE

        fun setToken(context : Context , token : String){

//            메모장을 불러내자.
            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)
//            불러낸 메모장에 token값 기록.

            pref.edit().putString(TOKEN,token).apply()


        }

           //  getter - 토큰 조회 기능.  LOAD

        fun getToken(context : Context) : String {

//            메모장을 불러내자

            val pref = context.getSharedPreferences(prefName,Context.MODE_PRIVATE)

//            불러낸 메모장에서 token 값 찾아서 리턴. (결과로 지정)

            return pref.getString(TOKEN,"")!!

        }

    }

}