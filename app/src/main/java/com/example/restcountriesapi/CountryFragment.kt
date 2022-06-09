package com.example.restcountriesapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.restcountriesapi.databinding.CountryFragmentBinding

class CountryFragment(
    private val data: CountryData,
    private val mainContext: Context,
    private val listener: Listener): Fragment() {

    interface Listener {
        fun onBorderClick(alpha3Code: String)
    }

    private lateinit var binding: CountryFragmentBinding
    private val name = data.getName()
    private val nativeName = data.getNativeName()
    private val population = data.getPopulation()
    private val region = data.getRegion()
    private val subregion = data.getSubregion()
    private val capital = data.getCapital()
    private val topLevelDomain = data.getTopLevelDomain()
    private val currencies = data.getCurrencies()
    private val languages = data.getLanguages()
    private val borders = data.getBorders()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.country_fragment, container, false)
        Glide.with(mainContext)
            .load(data.getFlag()?.png)
            .override(Target.SIZE_ORIGINAL)
            .into(binding.countryFlag)

        if (name != null) {
            binding.name.text = name
        }
        if (nativeName != null) {
            binding.nativeName.append(nativeName)
        }
        if (population != null) {
            binding.population.append(population)
        }
        if (region != null) {
            binding.region.append(region)
        }
        if (subregion != null) {
            binding.subregion.append(subregion)
        }
        if (capital != null) {
            binding.capital.append(capital)
        } else {
            binding.capital.append("None")
        }

        for (i in topLevelDomain?.indices!!) {
            binding.topLevelDomain.append("${topLevelDomain[i]}")
            if (i != topLevelDomain.size - 1) {
                binding.topLevelDomain.append(", ")
            }
        }

        for (i in currencies?.indices!!) {
            binding.currencies.append("${currencies[i].name}")
            if (i != currencies.size - 1) {
                binding.currencies.append(", ")
            }
        }

        for (i in languages?.indices!!) {
            binding.languages.append("${languages[i].name}")
            if (i != languages.size - 1) {
                binding.languages.append(", ")
            }
        }

        borders?.forEach {
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10, 10, 10, 10)
            val view = TextView(mainContext).apply {
                text = it
                textSize = 15f
                setBackgroundResource(R.drawable.shaded_background)
                setOnClickListener { listener.onBorderClick("${this.text}") }
            }



        }

        return binding.root
    }
}