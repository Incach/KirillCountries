package com.example.restcountriesapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.restcountriesapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.Serializable

class MainActivity : AppCompatActivity(), HomeFragment.Listener, CountryFragment.Listener {

    private lateinit var binding: ActivityMainBinding
    private val countries: ArrayList<CountryData> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        submitRequest()
    }

    private fun submitRequest() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://restcountries.com/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CountriesService::class.java)
        val call = service.getCountryData(getString(R.string.query_fields))

        call.enqueue(object : Callback<List<CountriesResponse>> {
            override fun onResponse(
                call: Call<List<CountriesResponse>>?,
                response: Response<List<CountriesResponse>>?
            ) {
                if (response?.code() == 200) {

                    response.body().forEach {
                        countries.add(CountryData().receiveAndSetValues(it))
                    }

                    val transaction = supportFragmentManager.beginTransaction()
                    val adapterFragment = HomeFragment(this@MainActivity, this@MainActivity)
                    val bundle = Bundle()

                    bundle.putSerializable("data", countries as Serializable)
                    adapterFragment.arguments = bundle
                    transaction.replace(R.id.fragment_container, adapterFragment).commit()

                    Toast.makeText(this@MainActivity, "Success!", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<CountriesResponse>>?, t: Throwable?) {
                Toast.makeText(this@MainActivity, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
                Log.e("RESPONSE", "$t")
            }
        })
    }

    override fun onBorderClick(alpha3Code: String) {
        lateinit var country: CountryData

        countries.forEach {
            if (it.getAlpha3Code() == alpha3Code) {
                country = it
            }
        }

        val transaction = this.supportFragmentManager.beginTransaction()
        val countryFragment = CountryFragment(country, this@MainActivity, this@MainActivity)

        transaction.replace(R.id.fragment_container, countryFragment).commit()
        Log.d("MAIN", "Clicked ${country.getName()}")
    }

    override fun onViewHolderClicked(name: String) {
        lateinit var country: CountryData

        countries.forEach {
            if (it.getName() == name) {
                country = it
            }
        }

        val transaction = this.supportFragmentManager.beginTransaction()
        val countryFragment = CountryFragment(country, this@MainActivity, this@MainActivity)

        transaction.replace(R.id.fragment_container, countryFragment).addToBackStack("homeFragment").commit()
        Log.d("MAIN", "Clicked ${country.getName()}")
    }
}