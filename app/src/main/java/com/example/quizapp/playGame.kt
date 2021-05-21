package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.quizapp.DBHelper.DBHelper
import com.example.quizapp.Model.Questions
import java.util.*
import kotlin.concurrent.schedule
import kotlinx.android.synthetic.main.activity_play_game.*

const val EXTRA_MESSAGE = "com.example.quizapp.MESSAGE"
const val TOTAL_QUESTIONS: String = "total_questions"
const val COREC_ANSWER = ""
private var mCorrectAnswers: Int=0
private var mCurrentPosition: Int =1
private var mang: Int = 5
private var mQuestionsList= arrayListOf<Questions>()
private var flag: Int = 1
class playGame : AppCompatActivity(), View.OnClickListener{

    private var mSelectedOptionPosition: Int = 0
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_play_game)
        imgBack.setOnClickListener {
            val intent: Intent = Intent( applicationContext, MainActivity::class.java)
            mCurrentPosition = 1
            mCorrectAnswers = 0
            flag = 1
            mang = 5
            startActivity(intent)
        }
        if (flag == 1 ) {
            mQuestionsList = DBHelper(this).getAllQuestions()
            mQuestionsList.shuffle()
            flag = 0
        }
        setQuestion()
        txtMang.text ="Ngu: " + mang.toString()
        txtSoCau.text = "Câu: " + mCurrentPosition.toString() + "/" + mQuestionsList?.size.toString()
        btna.setOnClickListener(this)
        btnb.setOnClickListener(this)
        btnc.setOnClickListener(this)
        btnd.setOnClickListener(this)
        }
    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNum
        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            tv.background = ContextCompat.getDrawable(this, R.drawable.confirm_btn)
        }
    }
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btna -> {
                selectedOptionView(btna, 1)
                click()
            }
            R.id.btnb -> {
                selectedOptionView(btnb, 2)
                click()
            }
            R.id.btnc -> {
                selectedOptionView(btnc, 3)
                click()
            }
            R.id.btnd -> {
                selectedOptionView(btnd, 4)
                click()
            }
        }
    }
    private fun click() {
            var question = mQuestionsList.get(mCurrentPosition - 1)
            if (question.cautl == mSelectedOptionPosition ) {
                val intent: Intent = Intent(applicationContext, giaithich::class.java)
                intent.putExtra(EXTRA_MESSAGE, question.caugiaithich)
                startActivity(intent)
                mCurrentPosition++
                Timer().schedule(10000000) {
                    setQuestion()
                    txtSoCau.text = "Câu: " + mCurrentPosition.toString() + "/" + mQuestionsList?.size.toString()
                }

                mCorrectAnswers++
                answerView(mSelectedOptionPosition, R.drawable.correct_option)
            }
             else if(question.cautl != mSelectedOptionPosition) {
                answerView(mSelectedOptionPosition, R.drawable.wrong_option)
                mang --

                txtMang.text ="Ngu: " + mang.toString()
                if (mang ==0) {
                    val intent: Intent = Intent( applicationContext, thuacuoc::class.java)
                    intent.putExtra(COREC_ANSWER, mCorrectAnswers)
                    intent.putExtra(TOTAL_QUESTIONS, mQuestionsList!!.size)
                    mCurrentPosition = 1
                    mCorrectAnswers = 0
                    flag = 1
                    mang = 5
                    startActivity(intent)
                    finish()
                }
            }
        }


     private fun setQuestion() {
        val questions = mQuestionsList!!.get(mCurrentPosition -1)
         defaultOptionsView()
        txtCauhoi.text = questions.cauhoi
        btna.text = questions.a
        btnb.text = questions.b
        btnc.text = questions.c
        btnd.text = questions.d

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add(0,btna)
        options.add(1,btnb)
        options.add(2,btnc)
        options.add(3,btnd)
        for(option in options ) {
            option.setTextColor(Color.parseColor("#363A43"))
            option.typeface = Typeface.DEFAULT
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                option.background = ContextCompat.getDrawable(this, R.drawable.mybutton)
            }

        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btna.isClickable = false
                    btna.background = ContextCompat.getDrawable(this, drawableView)
                }
            }
            2 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btnb.isClickable = false
                    btnb.background = ContextCompat.getDrawable(this, drawableView)
                }
            }
            3 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btnc.isClickable = false
                    btnc.background = ContextCompat.getDrawable(this, drawableView)

                }
            }
            4 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    btnd.isClickable = false
                    btnd.background = ContextCompat.getDrawable(this, drawableView)

                }
            }
        }
    }
}

