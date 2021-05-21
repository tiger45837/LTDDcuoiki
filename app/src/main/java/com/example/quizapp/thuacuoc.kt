package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.Model.heightScore
import kotlinx.android.synthetic.main.activity_thuacuoc.*

class thuacuoc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_thuacuoc)
        val diem = intent.getIntExtra(COREC_ANSWER, 1)
        btnThem.setOnClickListener {
            val ten = txtName.text.toString()
            val diemCao = heightScore()
            diemCao.ten = ten
            diemCao.diemCao = diem
            DBHelper(this).addScore(diemCao)
            intent.putExtra("diem",diem)
            val intent: Intent = Intent( applicationContext, diemcao::class.java)
            startActivity(intent)
        }

        val tongso = intent.getIntExtra(TOTAL_QUESTIONS,1)
        val phantram = ((diem.toFloat()/tongso.toFloat())*100)
        val b = (100-phantram).toInt()
        if(phantram == 0f ) {
            txttongso.text ="Vượt qua " + diem.toString() + "/" + tongso.toString() + ". Bạn đã ngu hơn 100% người chơi"
        }else {
            txttongso.text ="Vượt qua " + diem.toString() + "/" + tongso.toString() + ". Bạn đã ngu hơn "+  b.toString() + " %"+ "người chơi"
        }
        txtScore.text = "Điểm của bạn: " + diem.toString()
        btnChoilai.setOnClickListener {
            val intent: Intent = Intent( applicationContext, playGame::class.java)
            startActivity(intent)
        }
        btnMenu.setOnClickListener {
            val intent: Intent = Intent( applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}