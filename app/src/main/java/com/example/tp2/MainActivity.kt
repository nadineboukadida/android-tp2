package com.example.tp2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var matieres = arrayOf("Application mobile", "Anglais","Francais","AI")
    val spinner : Spinner by lazy { findViewById(R.id.spinner) }
    var etudiants = ArrayList<Etudiant>()
    @SuppressLint("MissingInflatedId")
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
        this.createStudents()

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        var adapter = EtudiantAdapter(etudiants)
        recyclerview.adapter = adapter
        val textInput : TextInputEditText = findViewById(R.id.textInputEditText)
        textInput.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                adapter = EtudiantAdapter(filter(s.toString()))
                recyclerview.adapter = adapter
        }
    })    }


    fun createStudents() {
        var genre :String = "female"
        for(i in 1..20){
            if(i%2 == 0){
                genre = "male"
            }else{
                genre = "female"
            }
            this.etudiants.add(Etudiant("name$i","surname$i",genre))
        }
    }

    fun filter (name : String) : ArrayList<Etudiant>{
        var filteredList = ArrayList<Etudiant>()
        for (student in etudiants) {
            if (student.nom.lowercase(Locale.ROOT)
                    .contains(name.lowercase(Locale.ROOT))
            ) {
                filteredList.add(student)
            }
        }
        return filteredList
    }
}