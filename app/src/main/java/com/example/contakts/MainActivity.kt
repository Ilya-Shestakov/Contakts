package com.example.contakts

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var btnAddMain1: Button
    lateinit var dbh: DBHalper
    private lateinit var newArry: ArrayList<Datalist>
    private lateinit var adapter: MyAdapter

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
        val newcursor: Cursor? = dbh!!.gettext()
        newArry = ArrayList<Datalist>()
        while (newcursor!!.moveToNext()){
            val uname = newcursor.getString(0)
            val uphone = newcursor.getString(1)
            newArry.add(Datalist(uname, uphone))
        }
        adapter = MyAdapter(newArry)
        recyclerView.adapter = adapter
        adapter.OnItemClickListener(object: MyAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, MainActivity3::class.java)
                intent.putExtra("name", newArry[position].name)
                intent.putExtra("phone", newArry[position].contact)
                startActivity(intent)
            }

        })
    }

}