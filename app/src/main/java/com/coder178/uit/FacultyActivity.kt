package com.coder178.uit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Adapter.FacultyAdapter
import com.coder178.uit.Adapter.GalleryAdapter
import com.coder178.uit.Data.Faculty
import com.coder178.uit.Data.Gallery
import com.coder178.uit.R
import com.google.firebase.firestore.*

class FacultyActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: FacultyAdapter
    lateinit var facultyArrayList: ArrayList<Faculty>
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faculty)


        recyclerView = findViewById(R.id.frv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        facultyArrayList = arrayListOf()
        myAdapter = FacultyAdapter(facultyArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListner()
    }
    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("Faculty").orderBy("facultyName", Query.Direction.DESCENDING).
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

                        facultyArrayList.add(dc.document.toObject(Faculty::class.java))

                    }
                }
                myAdapter.notifyDataSetChanged()
            }

        })
    }
}