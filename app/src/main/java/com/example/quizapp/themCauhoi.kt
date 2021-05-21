package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.Model.Questions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_them_cauhoi.*
import java.lang.Integer.parseInt

class themCauhoi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_them_cauhoi)
        btnGoi.setOnClickListener {
            val questions = Questions()
            questions.cauhoi = editThemCauHoi.text.toString()
            questions.a = editDapAn1.text.toString()
            questions.b = editDapAn2.text.toString()
            questions.c = editDapAn3.text.toString()
            questions.d = editDapAn4.text.toString()
            questions.cautl = parseInt(editDapAnDung.text.toString())
            questions.caugiaithich = editGiaiThich.text.toString()
            DBHelper(this).addQuestion(questions)
            val intent: Intent = Intent( applicationContext, daThem::class.java)
            startActivity(intent)
        }
    }
}