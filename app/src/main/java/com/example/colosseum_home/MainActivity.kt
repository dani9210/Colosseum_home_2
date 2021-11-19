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







    }

}