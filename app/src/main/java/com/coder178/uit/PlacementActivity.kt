package com.coder178.uit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Adapter.PlacementAdapter
import com.coder178.uit.Data.Placement
import com.google.firebase.firestore.*
import java.util.ArrayList

class PlacementActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var userArrayList: ArrayList<Placement>
    lateinit var myadapter: PlacementAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_placement)

        recyclerView = findViewById(R.id.prv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        userArrayList = arrayListOf()
        myadapter = PlacementAdapter(userArrayList)
        recyclerView.adapter = myadapter

        EventChangeListner()
    }

    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("Placement").orderBy("studentName", Query.Direction.ASCENDING).
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

                        userArrayList.add(dc.document.toObject(Placement::class.java))

                    }
                }
                myadapter.notifyDataSetChanged()
            }

        })
    }
}