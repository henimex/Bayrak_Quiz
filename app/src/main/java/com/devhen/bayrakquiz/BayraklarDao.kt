package com.devhen.bayrakquiz

import android.content.ContentValues

class BayraklarDao {

    fun rastgele5bayrak(vt:VeritabanıYardımcı):ArrayList<Bayraklar>{

        val bayraklarListe = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar ORDER BY RANDOM() LIMIT 5",null)

        while (c.moveToNext()) {
            val bayrak = Bayraklar(
                c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim"))
            )
            bayraklarListe.add(bayrak)
        }
        return bayraklarListe
    }

    fun yanlisCevap(vt:VeritabanıYardımcı,bayrak_id:Int) :ArrayList<Bayraklar>{

        val bayraklarListe = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val c = db.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id !=$bayrak_id ORDER BY RANDOM() LIMIT 3",null)

        while (c.moveToNext()) {
            val bayrak = Bayraklar(
                c.getInt(c.getColumnIndex("bayrak_id")),
                c.getString(c.getColumnIndex("bayrak_ad")),
                c.getString(c.getColumnIndex("bayrak_resim"))
            )
            bayraklarListe.add(bayrak)
        }
        return bayraklarListe
    }

    fun tumBayraklar(vt: VeritabanıYardımcı): ArrayList<Bayraklar> {

        val bayraklarListe = ArrayList<Bayraklar>()
        val db = vt.writableDatabase
        val cursor = db.rawQuery("SELECT * FROM bayraklar", null)

        while (cursor.moveToNext()) {
            val bayrak = Bayraklar(
                cursor.getInt(cursor.getColumnIndex("bayrak_id")),
                cursor.getString(cursor.getColumnIndex("bayrak_ad")),
                cursor.getString(cursor.getColumnIndex("bayrak_resim")),
                )
            bayraklarListe.add(bayrak)
        }
        return bayraklarListe
    }
}