package com.priyanshu.rateapp.ui.history

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.priyanshu.rateapp.R

class HistoryFragment : Fragment() {

    lateinit var mrecyclerview: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var nDialog: ProgressDialog


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
         val root = inflater.inflate(R.layout.fragment_history, container, false)

        nDialog=ProgressDialog.show(activity,"The RateApp","Loading",true);
        mrecyclerview = root.findViewById(R.id.rating_history)


        FirebaseDatabaseHelper().readUsers(object : FirebaseDatabaseHelper.DataStatus
        {

            override fun DataIsLoaded(rate: MutableList<Rating>, keys: MutableList<String>) {
                activity?.let { RecyclerView_Config().setConfig(mrecyclerview, it,rate,keys) }
                nDialog.dismiss()

            }
        })
        return root
    }
}
