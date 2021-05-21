package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_da_them.*

class daThem : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_da_them)
        txtDaThem.text = "Câu đố của bạn đã được thêm vào trò chơi ! Cảm ơn bạn đã đóng góp làm phong phú thêm câu hỏi của trò chơi !!"
        btnTrove.setOnClickListener {
            val intent: Intent = Intent( applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}