package com.example.quizapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.Model.heightScore
import kotlinx.android.synthetic.main.activity_diemcao.*

class diemcao : AppCompatActivity() {
    private var list: ArrayList<heightScore>? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_diemcao)
        list = DBHelper(this).getScore()
        txtTen.text =  list!!.get(0).ten
        txtDiemcao.text = list!!.get(0).diemCao.toString()
        Menu.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}


