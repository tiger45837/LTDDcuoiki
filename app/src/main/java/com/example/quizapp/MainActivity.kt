package com.example.quizapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_main)
        btnChoiGame.setOnClickListener() {
            val intent: Intent = Intent( applicationContext, playGame::class.java)
            startActivity(intent)
        }
        btnBXH.setOnClickListener {
            val intent: Intent = Intent( applicationContext, diemcao::class.java)
            startActivity(intent)
        }
        btnThemCauhoi.setOnClickListener {
            val intent: Intent = Intent( applicationContext, themCauhoi::class.java)
            startActivity(intent)
        }

    }
}
