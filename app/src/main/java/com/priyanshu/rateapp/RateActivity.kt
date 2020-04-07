package com.priyanshu.rateapp

import android.icu.text.SimpleDateFormat
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.View
import android.widget.SeekBar
import androidx.annotation.RequiresApi
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_rate.*
import java.lang.Integer.parseInt
import java.sql.Time
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.HashMap

class RateActivity : AppCompatActivity() {
    lateinit var range:String
    lateinit var current:Date
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rate)
        var rate = findViewById<SeekBar>(R.id.ratebar)
        var databaseRefrence = FirebaseDatabase.getInstance().getReference("range")
        databaseRefrence.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
               range =p0.value.toString()
                rate.max=range[2].toInt()-range[0].toInt()
                rate.progress=0
            }

    })
        rate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rating.text=(progress.toInt()+ parseInt(range[0].toString())).toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        submit_rating.setOnClickListener (object : View.OnClickListener{
            override fun onClick(v: View?) {
                current = Calendar.getInstance().time
                var df= SimpleDateFormat("dd-MM-yyyy")
                var now = SimpleDateFormat("HH:mm:ss")

                val RateHashMap = HashMap<String, Any>()
                RateHashMap["date"] =df.format(current)
                RateHashMap["time"] = now.format(current)
                RateHashMap["range"] = range
                RateHashMap["rate"]  = rating.text
                val submit_rate = FirebaseDatabase.getInstance().reference.child("Rating").child("$current")
                submit_rate.updateChildren(RateHashMap).addOnCompleteListener {
                    task ->
                    if(task.isSuccessful)
                    {
                        rate.progress=0
                    }
                }

            }

        }
        )
}
}
