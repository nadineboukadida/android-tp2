package com.example.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class EtudiantAdapter(private val data: ArrayList<Etudiant>) :
    RecyclerView.Adapter<EtudiantAdapter.ViewHolder>(), Filterable {
    var dataFilterList = ArrayList<Etudiant>()

    init {
        dataFilterList = data
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView>(R.id.imageView)
        val textView = itemView.findViewById<TextView>(R.id.textView)
        val checkBoxPresent = itemView.findViewById<CheckBox>(R.id.checkBoxPresent)
        val checkBoxAbsent = itemView.findViewById<CheckBox>(R.id.checkBoxAbsent)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EtudiantAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.student_item, parent, false)
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(viewHolder: EtudiantAdapter.ViewHolder, position: Int) {
        val student: Etudiant = dataFilterList.get(position)
        val textView = viewHolder.textView
        val imageView = viewHolder.imageView
        val checkBoxPresent = viewHolder.checkBoxPresent
        val checkBoxAbsent = viewHolder.checkBoxAbsent
        checkBoxAbsent.setOnClickListener{
            student.status = Status.absent
            checkBoxAbsent.isChecked = true
            checkBoxPresent.isChecked  = false
        }
        checkBoxPresent.setOnClickListener(){
            student.status = Status.present
            checkBoxAbsent.isChecked = false
            checkBoxPresent.isChecked  = true
        }
        textView.setText(student.nom + ' ' + student.prenom)
        if (student.genre == "male") {
            imageView.setImageResource(R.drawable.man)
        } else {
            imageView.setImageResource(R.drawable.woman)
        }
        checkBoxPresent.isChecked = student.status.equals(Status.present)
        checkBoxAbsent.isChecked = student.status.equals(Status.absent)
    }

    override fun getItemCount(): Int {
        return dataFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    dataFilterList = data
                } else {
                    var keys : List<String>  = constraint.toString().split(":")
                    val resultat = ArrayList<Etudiant>()
                    if(keys[0]=="nom") {
                        for (etudiant in data) {
                            if (etudiant.prenom.lowercase(Locale.ROOT)
                                    .contains(keys[1].lowercase(Locale.ROOT)) || etudiant.nom.lowercase(
                                    Locale.ROOT
                                ).contains(keys[1].lowercase(Locale.ROOT))
                            ) {
                                resultat.add(etudiant)
                            }
                        }
                    }
                    else {
                        for(etudiant in data){
                            if(etudiant.status.toString()==keys[1])
                                resultat.add(etudiant)
                        }
                    }
                    dataFilterList = resultat
                }
                val res = FilterResults()
                res.values = dataFilterList
                return res
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Etudiant>
                notifyDataSetChanged()
            }

        }
    }
}