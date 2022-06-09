package com.example.restcountriesapi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

class RecyclerAdapter(
    private val data: ArrayList<CountryData>,
    private val context: Context,
    private val listener: Listener):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    interface Listener {
        fun onItemClicked(name: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.country_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        try {
            Glide.with(context)
                .load(data[position].getFlag()?.png)
                .into(holder.flag)
        } catch (e: Exception) {
            Log.e("ERROR", "$e")
        }

        holder.flag.apply {
            clipToOutline = true
        }

        holder.name.text = data[position].getName()
        //holder.population.text = "Population: " + data[position].getPopulation()
        holder.region.text = "Регион: " + data[position].getRegion()

        if (data[position].getCapital() != null) {
            holder.capital.text = "Столица: " + data[position].getCapital()
        } else { holder.capital.text = "Столица: Нет" }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        var flag: ImageView = itemView.findViewById(R.id.card_flag)
        var name: TextView = itemView.findViewById(R.id.card_name)
        //var population: TextView = itemView.findViewById(R.id.card_population)
        var region: TextView = itemView.findViewById(R.id.card_region)
        var capital: TextView = itemView.findViewById(R.id.card_capital)

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClicked("${this.name.text}")
        }
    }
}