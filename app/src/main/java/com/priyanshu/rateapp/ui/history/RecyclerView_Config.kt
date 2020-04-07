package com.priyanshu.rateapp.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.priyanshu.rateapp.R


internal class RecyclerView_Config
{


    private lateinit var mContext: Context
    private lateinit var ratingAdapter: RatingAdapter
    fun setConfig(recyclerView: RecyclerView, context: Context, rate:MutableList<Rating>, keys:MutableList<String>)
    {
        mContext=context
        ratingAdapter = RatingAdapter(rate,keys)
        recyclerView.layoutManager= LinearLayoutManager(context)
        recyclerView.adapter=ratingAdapter

    }
    inner class RatingItemView(parent: ViewGroup):
        RecyclerView.ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.rate, parent, false))
    {
        private  var Date: TextView = itemView.findViewById(R.id.Date)
        private  var Time: TextView = itemView.findViewById(R.id.Time)
        private  var Range: TextView = itemView.findViewById(R.id.Range)
        private  var Rating: TextView = itemView.findViewById(R.id.Rating)
        private lateinit var key0_text:String



        fun bind(rate:Rating, key:String)
        {
            Date.text=rate.get_date()
            Time.text=rate.get_time()
            Range.text=rate.get_range()
            Rating.text=rate.get_rate()
            key0_text=key
        }
    }
    inner class RatingAdapter : RecyclerView.Adapter<RatingItemView>
    {

        private var RateList = mutableListOf<Rating>()
        private var mKeys = mutableListOf<String>()
        constructor()
        constructor(rateList:MutableList<Rating>, mKeys:MutableList<String>)
        {
            this.mKeys=mKeys
            this.RateList=rateList
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatingItemView {
            return RatingItemView(parent)
        }

        override fun getItemCount(): Int {
            return mKeys.size
        }

        override fun onBindViewHolder(holder: RatingItemView, position: Int) {
            holder.bind(RateList.get(position),mKeys.get(position))
        }
    }
}
