package com.example.contakts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    private lateinit var db: DBHalper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var name = findViewById<EditText>(R.id.editTextText)
        var phone = findViewById<EditText>(R.id.editTextText2)
        val del = findViewById<Button>(R.id.button)
        val edit = findViewById<Button>(R.id.button2)

        db = DBHalper(this)

        del.setText(intent.getStringExtra(""))
        edit.setText(intent.getStringExtra(""))

        del.setOnClickListener {
            val names = name.text.toString()
            val deletedata = db.deleteuserdata(names)
            if (deletedata==true){
                Toast.makeText(this, "Delete contact", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
        }

        edit.setOnClickListener {
            val names = name.text.toString()
            val numbers = phone.text.toString()
            val updatadata = db.updatauserdata(names, numbers)

            if (updatadata==true){
                Toast.makeText(this, "Update contact", Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}