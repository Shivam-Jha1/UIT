package com.coder178.uit.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Data.Notice
import com.coder178.uit.R
import com.squareup.picasso.Picasso
import java.text.DateFormat
import java.util.*

class NoticeAdapter(val context: Context, val users:ArrayList<Notice>):RecyclerView.Adapter<UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.notice_list,parent,false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user = users[position]
        holder.name.text = user.name
        holder.date.text = user.date
//        Glide.with(context)
//            .load(user.image)
//            .into(holder.image)
        Picasso.get()
            .load(user.image)
            .into(holder.image)
    }

    override fun getItemCount(): Int {
        return users.size
    }
}

class UserViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    val name:TextView = itemView.findViewById(R.id.noticeText)
    val date:TextView = itemView.findViewById(R.id.noticeTime)
    val image:ImageView = itemView.findViewById(R.id.noticeImage)

}