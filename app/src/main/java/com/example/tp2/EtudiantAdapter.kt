package com.example.tp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class EtudiantAdapter(private val data: ArrayList<Etudiant>) :
    RecyclerView.Adapter<EtudiantAdapter.ViewHolder>() , Filterable {

    var dataFilterList = ArrayList<Etudiant>()
    init {
        dataFilterList = data
    }


    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val textView: TextView = itemView.findViewById(R.id.textView)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            EtudiantAdapter.ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.student_item, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val itemsViewModel = data[position]
        if(itemsViewModel.genre == "male"){
            holder.imageView.setImageResource(R.drawable.man)
        }else{
            holder.imageView.setImageResource(R.drawable.woman)
        }
        holder.textView.text = itemsViewModel.nom + " " + itemsViewModel.prenom
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = data
                } else {
                    val resultList = ArrayList<Etudiant>()
                    for (student in data) {
                        if (student.nom.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(student)
                        }
                    }
                    dataFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as ArrayList<Etudiant>
                notifyDataSetChanged()
            }

        }
    }




}