package com.priyanshu.rateapp.ui.history


import com.google.firebase.database.*

open class FirebaseDatabaseHelper
{

    private var mDatabase:FirebaseDatabase
    private var mRef:DatabaseReference
    private var list = mutableListOf<Rating>()

    interface DataStatus{
        fun DataIsLoaded(rate:MutableList<Rating>, keys:MutableList<String>)
    }
    init {
        mDatabase= FirebaseDatabase.getInstance()
        mRef=mDatabase.getReference("Rating")
    }


    fun readUsers(dataStatus: DataStatus)
    {

        mRef.addValueEventListener(object : ValueEventListener
        {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                var keys= mutableListOf<String>()
                for(keynode:DataSnapshot in p0.children )
                {
                    keys.add(keynode.key!!)
                    var rate:Rating? = keynode!!.getValue(Rating::class.java!!)
                    list.add(rate!!)
                }
                dataStatus.DataIsLoaded(list,keys)
            }

        })
    }
}