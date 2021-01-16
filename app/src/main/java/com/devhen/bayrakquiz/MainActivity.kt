package com.devhen.bayrakquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vt = VeritabanıYardımcı(this)

        btnStart.setOnClickListener {
            startActivity(Intent(this@MainActivity,QuizActivity::class.java))
        }

        //copyDBtoDevice()

        val tumBayraklar = BayraklarDao().tumBayraklar(vt)

        for (i in tumBayraklar){
            Log.e("bayrak_id",i.bayrak_id.toString())
            Log.e("bayrak_ad",i.bayrak_ad)
            Log.e("bayrak_resim",i.bayrak_resim)
        }
    }

    fun copyDBtoDevice(){
        val copyHelper=DatabaseCopyHelper(this)
        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}