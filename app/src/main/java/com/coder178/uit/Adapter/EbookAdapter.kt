package com.coder178.uit.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Data.Ebook
import com.coder178.uit.R

class EbookAdapter(val pdfs:ArrayList<Ebook>):RecyclerView.Adapter<EbookViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EbookViewHolder {
        return EbookViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ebook_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: EbookViewHolder, position: Int) {
        val pdf = pdfs[position]
        holder.pdfName.text = pdf.pdfName
        holder.pdfDate.text = pdf.pdfDate
        holder.pdfSize.text = pdf.pdfSize
    }

    override fun getItemCount(): Int {
        return pdfs.size
    }
}

class EbookViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    val pdfName:TextView = itemView.findViewById(R.id.pdfName)
    val pdfSize:TextView = itemView.findViewById(R.id.pdfSize)
    val pdfDate:TextView = itemView.findViewById(R.id.pdfDate)

}
