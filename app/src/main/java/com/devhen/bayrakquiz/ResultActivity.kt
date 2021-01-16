package com.devhen.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val dogruSayac = intent.getIntExtra("dogruSayac",0)

        txtSonuc.text = "Doğru : $dogruSayac Yanlış : ${5-dogruSayac}"
        txtYuzdeSonuc.text = "% ${(dogruSayac*100)/5} Başarı Oranı"

        btnTekrarDene.setOnClickListener {
            startActivity(Intent(this@ResultActivity,QuizActivity::class.java))
            finish()
        }
    }
}