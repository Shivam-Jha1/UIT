package com.coder178.uit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coder178.uit.Adapter.EbookAdapter
import com.coder178.uit.Adapter.FacultyAdapter
import com.coder178.uit.Data.Ebook
import com.coder178.uit.Data.Faculty
import com.coder178.uit.R
import com.google.firebase.firestore.*

class EbookActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: EbookAdapter
    lateinit var EbookArrayList: ArrayList<Ebook>
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ebook)


        recyclerView = findViewById(R.id.erv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        EbookArrayList = arrayListOf()
        myAdapter = EbookAdapter(EbookArrayList)
        recyclerView.adapter = myAdapter

        EventChangeListner()
    }
    private fun EventChangeListner() {
        db = FirebaseFirestore.getInstance()
        db.collection("Pdf").orderBy("pdfDate", Query.Direction.DESCENDING).
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

                        EbookArrayList.add(dc.document.toObject(Ebook::class.java))

                    }
                }
                myAdapter.notifyDataSetChanged()
            }

        })
    }
}