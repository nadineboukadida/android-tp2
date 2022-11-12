package com.example.tp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    var matieres = arrayOf("Application mobile", "Anglais","Francais","AI")
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spinner.adapter = ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,matieres)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(
                    applicationContext,
                    "course selected: " + adapterView?.getItemAtPosition(position)
                        .toString(),
                    Toast.LENGTH_SHORT
                ).show();
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }

    }


}