package com.coder178.uit.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Adapter.NoticeAdapter
import com.coder178.uit.Data.Notice
import com.coder178.uit.R
import com.google.firebase.firestore.*
import java.util.ArrayList

class NoticeActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var userArrayList: ArrayList<Notice>
    lateinit var myadapter: NoticeAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)

        recyclerView = findViewById(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myadapter = NoticeAdapter(this,userArrayList)
        recyclerView.adapter = myadapter

        EventChangeListner()
    }

    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("Notice").orderBy("date", Query.Direction.DESCENDING).
        addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ) {

                if(error!=null){
                    Log.e("firestore error",error.message.toString())
                    return
                }
                for(dc : DocumentChange in value?.documentChanges!!){

                    if(dc.type == DocumentChange.Type.ADDED){

                        userArrayList.add(dc.document.toObject(Notice::class.java))

                    }
                }
                myadapter.notifyDataSetChanged()
            }

        })
    }
}