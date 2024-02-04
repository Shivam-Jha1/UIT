package com.coder178.uit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Data.Faculty
import com.coder178.uit.R
import com.squareup.picasso.Picasso

class FacultyAdapter(val faculty:ArrayList<Faculty>):RecyclerView.Adapter<facultyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): facultyViewHolder {
        return facultyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.faculty_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: facultyViewHolder, position: Int) {
        val faculties = faculty[position]
        holder.facultyName.text = faculties.facultyName
        holder.facultyBranch.text = faculties.facultyBranch

        Picasso.get()
            .load(faculties.facultyImage)
            .into(holder.facultyImage)
    }

    override fun getItemCount(): Int {
        return faculty.size
    }
}

class facultyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {

    val facultyName:TextView = itemView.findViewById(R.id.facultyName)
    val facultyBranch:TextView = itemView.findViewById(R.id.facultyBranch)
    val facultyImage:ImageView = itemView.findViewById(R.id.facultyImage)

}
