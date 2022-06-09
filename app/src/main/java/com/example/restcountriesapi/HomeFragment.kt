package com.example.restcountriesapi

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.restcountriesapi.databinding.HomeFragmentBinding

class HomeFragment(private val mainContext: Context, private val listener: Listener): Fragment(),
    RecyclerAdapter.Listener, PopupMenu.OnMenuItemClickListener {

    interface Listener {
        fun onViewHolderClicked(name: String)
    }

    private lateinit var binding: HomeFragmentBinding
    private val countries: ArrayList<CountryData> = arrayListOf()
    private var displayCountries: ArrayList<CountryData> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.home_fragment, container, false)

        if (arguments != null) {
            val args = arguments?.getSerializable("data") as ArrayList<*>
            args.forEach {
                countries.add(it as CountryData)
            }

            countries.forEach {
                displayCountries.add(it)
            }
        }

        binding.countryRecycler.adapter = RecyclerAdapter(
            displayCountries,
            mainContext,
            this@HomeFragment
        )

        binding.search.apply {
            doOnTextChanged { text, _, _, _ ->
                displayCountries.clear()
                countries.forEach {
                    if (it.getName()?.lowercase()?.contains(text.toString()) == true) {
                        displayCountries.add(it)
                    }
                }

                binding.countryRecycler.adapter?.notifyDataSetChanged()
            }
        }

        binding.btnShow.setOnClickListener {
            showMenu(it)
        }

        return binding.root
    }

    private fun showMenu(it: View?) {
        val popup = PopupMenu(mainContext, it)
        popup.setOnMenuItemClickListener(this@HomeFragment)
        popup.inflate(R.menu.menu_example)
        popup.show()
    }

    override fun onItemClicked(name: String) {
        listener.onViewHolderClicked(name)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.title) {
            "All" -> {
                displayCountries.clear()
                countries.forEach {
                    displayCountries.add(it) }
            }
            "Africa" -> { filterByRegion("Africa") }
            "Americas" -> { filterByRegion("Americas") }
            "Asia" -> { filterByRegion("Asia") }
            "Europe" -> { filterByRegion("Europe") }
            "Oceania" -> { filterByRegion("Oceania") }
            else -> false
        }

        return true
    }

    private fun filterByRegion(region: String) {
        binding.btnShow.text = region
        displayCountries.clear()
        countries.forEach {
            if (it.getRegion() == region) {
                displayCountries.add(it)
            }
        }

        binding.countryRecycler.adapter?.notifyDataSetChanged()
    }
}
