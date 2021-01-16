package com.devhen.bayrakquiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    private lateinit var sorular: ArrayList<Bayraklar>
    private lateinit var yanlisSecenekler: ArrayList<Bayraklar>
    private lateinit var dogruSoru: Bayraklar
    private lateinit var tumSecenekler: HashSet<Bayraklar>
    private lateinit var vt : VeritabanıYardımcı

    private var soruSayac = 0
    private var dogruSayac = 0
    private var yanlisSayac = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        vt = VeritabanıYardımcı(this)
        sorular = BayraklarDao().rastgele5bayrak(vt)
        soruYukle()

        btnA.setOnClickListener {
            dogruKontrol(btnA)
            soruSayacKontrol()
        }

        btnB.setOnClickListener {
            dogruKontrol(btnB)
            soruSayacKontrol()
        }

        btnC.setOnClickListener {
            dogruKontrol(btnC)
            soruSayacKontrol()
        }

        btnD.setOnClickListener {
            dogruKontrol(btnD)
            soruSayacKontrol()
        }
    }

    fun soruYukle(){
        txtSoru.text = "${soruSayac+1} .Soru "
        dogruSoru= sorular.get(soruSayac)
        imgBayrak.setImageResource(resources.getIdentifier(dogruSoru.bayrak_resim,"drawable",packageName))

        yanlisSecenekler = BayraklarDao().yanlisCevap(vt,dogruSoru.bayrak_id)
        tumSecenekler = HashSet()
        tumSecenekler.add(dogruSoru)
        tumSecenekler.add(yanlisSecenekler.get(0))
        tumSecenekler.add(yanlisSecenekler.get(1))
        tumSecenekler.add(yanlisSecenekler.get(2))

        btnA.text = tumSecenekler.elementAt(0).bayrak_ad
        btnB.text = tumSecenekler.elementAt(1).bayrak_ad
        btnC.text = tumSecenekler.elementAt(2).bayrak_ad
        btnD.text = tumSecenekler.elementAt(3).bayrak_ad

    }

    fun soruSayacKontrol(){
        soruSayac++

        if (soruSayac!=5){
            soruYukle()
        }else{
            startActivity(Intent(this@QuizActivity, ResultActivity::class.java).
            putExtra("dogruSayac",dogruSayac).
            putExtra("yanlisSayac",yanlisSayac)
            )
            finish()
        }
    }

    fun dogruKontrol(button:Button){
        val btnYazi = button.text.toString()
        val dogruCevap = dogruSoru.bayrak_ad

        if (btnYazi == dogruCevap){
            dogruSayac++

        }else{
            yanlisSayac++
        }

        txtDogru.text = "Doğru : $dogruSayac"
        txtYanlis.text = "Yanlış : $yanlisSayac"
    }
}