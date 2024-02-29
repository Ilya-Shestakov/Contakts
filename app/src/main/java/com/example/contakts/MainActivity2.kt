package com.example.contakts

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity2 : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var phone: EditText
    private lateinit var db: DBHalper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        name = findViewById(R.id.name)
        phone = findViewById(R.id.phone)
        db = DBHalper(this)
    }

    fun saveMethod(view: View){
        val names = name.text.toString()
        val numbers = phone.text.toString()
        val savedata = db.saveuserdata(names, numbers)
        if (TextUtils.isEmpty(names) || TextUtils.isEmpty(numbers)){
            Toast.makeText(this, "Add name & phone", Toast.LENGTH_SHORT).show()
        }
        else
        {
            if (savedata==true){
                Toast.makeText(this, "Save contact", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Exist contact", Toast.LENGTH_SHORT).show()
            }
        }
    }
}