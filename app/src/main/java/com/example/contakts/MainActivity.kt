package com.example.contakts

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var btnAddMain1: Button
    lateinit var dbh: DBHalper
    private lateinit var newArry: ArrayList<Datalist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        btnAddMain1 = findViewById(R.id.btnAddMain1)

        btnAddMain1.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        dbh = DBHalper(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        dispayuser()

    }

    private fun dispayuser() {
        var newcursor: Cursor? = dbh!!.gettext()
        newArry = ArrayList<Datalist>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            val uphone = newcursor.getString(1)
            newArry.add(Datalist(uname, uphone))
        }
        recyclerView.adapter = MyAdapter(newArry)
    }

}