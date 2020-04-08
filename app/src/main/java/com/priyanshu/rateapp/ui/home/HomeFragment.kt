package com.priyanshu.rateapp.ui.home

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.*
import com.priyanshu.rateapp.R
import com.priyanshu.rateapp.RateActivity

class HomeFragment : Fragment() {
    lateinit var nDialog:ProgressDialog
    lateinit var databaseRefrence: DatabaseReference
    lateinit var range:String
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
          val root = inflater.inflate(R.layout.fragment_home, container, false)
        nDialog= ProgressDialog.show(activity,"The RateApp","Loading",true);
        databaseRefrence = FirebaseDatabase.getInstance().getReference("range")
        databaseRefrence.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }


            @SuppressLint("SetTextI18n")
            override fun onDataChange(p0: DataSnapshot) {

                range = p0.value.toString()
                root.findViewById<Button>(R.id.submit).text = "Rating ${range}"
                nDialog.dismiss()
            }



        })
        root.findViewById<Button>(R.id.submit).setOnClickListener {
            nDialog=ProgressDialog.show(activity,"The RateApp","Loading",true);
            val intent =Intent(activity,RateActivity::class.java)
            startActivity(intent)
            nDialog.dismiss()
        }
            return root

    }
}
