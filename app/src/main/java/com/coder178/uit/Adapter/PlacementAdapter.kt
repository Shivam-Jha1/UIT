package com.coder178.uit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Data.Placement
import com.coder178.uit.R
import com.squareup.picasso.Picasso

class PlacementAdapter(val students:ArrayList<Placement>):RecyclerView.Adapter<studentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): studentViewHolder {
        return studentViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.placement_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: studentViewHolder, position: Int) {
        val student = students[position]
        holder.studentName.text = student.studentName
        holder.studentBranch.text = student.studentBranch
        holder.companyName.text = student.companyName

        Picasso.get()
            .load(student.studentImage)
            .into(holder.StudentImage)
    }

    override fun getItemCount(): Int {
        return students.size
    }
}

class studentViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    val studentName:TextView = itemView.findViewById(R.id.studentName)
    val studentBranch:TextView = itemView.findViewById(R.id.studentBranch)
    val companyName:TextView = itemView.findViewById(R.id.companyName)
    val StudentImage:ImageView = itemView.findViewById(R.id.studentImage)

}
