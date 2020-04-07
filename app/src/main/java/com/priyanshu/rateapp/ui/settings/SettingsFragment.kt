package com.priyanshu.rateapp.ui.settings

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.priyanshu.rateapp.R
import java.lang.StringBuilder
import java.text.FieldPosition

class SettingsFragment : Fragment() {

    var range= StringBuilder("0-9")
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
           val root = inflater.inflate(R.layout.fragment_setting, container, false)

        val min=root.findViewById(R.id.min) as Spinner
        val max=root.findViewById(R.id.max) as Spinner
        val minValue= mutableListOf<Int>(0,1,2,3,4,5,6,7,8)
        var maxValue= mutableListOf<Int>(9,8,7,6,5,4,3,2,1)
        min.adapter=ArrayAdapter(context!!,android.R.layout.simple_spinner_dropdown_item,minValue)
        min.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
               //
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                try {
                    var selected = parent?.getItemAtPosition(position).toString()
                    when (selected) {
                        "0" -> {
                            maxValue.clear()
                            maxValue.addAll(9 downTo 1)
                            range.setCharAt(0,'0')
                        }
                        "1" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 2)
                            range.setCharAt(0,'1')
                        }
                        "2" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 3)
                            range.setCharAt(0,'2')
                        }
                        "3" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 4)
                            range.setCharAt(0,'3')
                        }
                        "4" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 5)
                            range.setCharAt(0,'4')
                        }
                        "5" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 6)
                            range.setCharAt(0,'5')
                        }
                        "6" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 7)
                            range.setCharAt(0,'6')
                        }
                        "7" -> {

                            maxValue.clear()
                            maxValue.addAll(9 downTo 8)
                            range.setCharAt(0,'7')
                        }
                        "8" -> {

                            maxValue.clear()
                            maxValue.add(9)
                            range.setCharAt(0,'8')
                        }
                        else -> return
                    }


                }
                catch (e:Exception)
                {

                }
                max.onItemSelectedListener = object:AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        //
                    }

                    @RequiresApi(Build.VERSION_CODES.N)
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        try {
                            var selected = parent?.getItemAtPosition(position).toString()
                            when (selected) {
                                "9" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..8)
                                    range.setCharAt(2, '9')
                                }
                                "8" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..7)
                                    range.setCharAt(2, '8')
                                }
                                "7" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..6)
                                    range.setCharAt(2, '7')
                                }
                                "6" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..5)
                                    range.setCharAt(2, '6')
                                }
                                "5" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..4)
                                    range.setCharAt(2, '5')
                                }
                                "4" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..3)
                                    range.setCharAt(2, '4')
                                }
                                "3" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..2)
                                    range.setCharAt(2, '3')
                                }
                                "2" ->
                                {
                                    minValue.clear()
                                    minValue.addAll(0..1)
                                    range.setCharAt(2, '2')
                                }
                                "1" ->
                                {
                                    minValue.clear()
                                    minValue.add(0)
                                    range.setCharAt(2,'1')
                                }
                                else -> return
                            }
                        } catch (e: java.lang.Exception) {
                        }
                    }
                    }
            }

        }
             max.adapter=ArrayAdapter(context!!,android.R.layout.simple_spinner_dropdown_item,maxValue)


        root.findViewById<Button>(R.id.save).setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {

                    val refUsers = FirebaseDatabase.getInstance().getReference("range")
                    refUsers.setValue(range.toString()).addOnCompleteListener{
                        task ->
                        if (task.isSuccessful)
                        {
                            val dialogBuilder =
                                android.app.AlertDialog.Builder(
                                    activity
                                )
                            dialogBuilder.setMessage("Range Updated Successfully!!")
                                .setCancelable(false)
                                .setPositiveButton(
                                    "OK",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        dialog.dismiss()
                                    })

                            val alert = dialogBuilder.create()
                            alert.setTitle("The RateApp")
                            alert.show()
                        }
                        else
                        {
                            val dialogBuilder =
                                android.app.AlertDialog.Builder(
                                    activity
                                )
                            dialogBuilder.setMessage("Could not Process At this moment !!")
                                .setCancelable(false)
                                .setPositiveButton(
                                    "OK",
                                    DialogInterface.OnClickListener { dialog, id ->
                                        dialog.dismiss()
                                    })

                            val alert = dialogBuilder.create()
                            alert.setTitle("The RateApp")
                            alert.show()
                        }
                    }
                }


        })
            return root
        }
}
