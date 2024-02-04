package com.coder178.uit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Adapter.GalleryAdapter
import com.coder178.uit.Data.Gallery
import com.coder178.uit.Data.Notice
import com.coder178.uit.R
import com.google.firebase.firestore.*

class GalleryActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: GalleryAdapter
    lateinit var galleryArrayList: ArrayList<Gallery>
    lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        recyclerView = findViewById(R.id.grv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        galleryArrayList = arrayListOf()
        myAdapter = GalleryAdapter(galleryArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListner()
    }
        private fun EventChangeListner() {
            db = FirebaseFirestore.getInstance()
            db.collection("Gallery").
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

                            galleryArrayList.add(dc.document.toObject(Gallery::class.java))

                        }
                    }
                    myAdapter.notifyDataSetChanged()
                }

            })
        }
    }