package com.example.tp2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import java.util.Locale.filter
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    val presentBtn : Button by lazy {findViewById(R.id.presentButton)}
    val absentBtn : Button by lazy {findViewById(R.id.absentButton)}
    val renitializeBtn : Button by lazy{findViewById(R.id.renitializeButton)}
    lateinit var searchInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var etudiants = arrayListOf<Etudiant>(
            Etudiant("Ncib", "Nawres", "female", Status.present),
            Etudiant("Boukadida", "Nadine","female", Status.present),
            Etudiant("Jerbi", "Taher", "male", Status.absent),
            Etudiant("Fayadh", "Mirna","female", Status.present),
            Etudiant("Abdennabih", "Ahmed", "male", Status.absent),
            Etudiant("Hani", "Evangeline","female", Status.present),
            Etudiant("Bianco", "Lily", "female", Status.absent),
            Etudiant("Laklouka", "Luka", "male", Status.present),
            )

        var adapter:EtudiantAdapter = EtudiantAdapter(etudiants)
        var listEtudiants= findViewById<View>(R.id.recyclerview) as RecyclerView
        var filter = adapter.filter
        listEtudiants.adapter = adapter
        listEtudiants.layoutManager = LinearLayoutManager(this)
        searchInput = findViewById(R.id.textInputEditText)
        searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                filter.filter("nom:$p0")
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
        presentBtn.setOnClickListener{
            filter.filter("status:present")
        }
        absentBtn.setOnClickListener{
            filter.filter("status:absent")
        }
        renitializeBtn.setOnClickListener{
            filter.filter("")
            searchInput.setText("")
        }
    }

}