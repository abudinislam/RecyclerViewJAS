package kz.abudinislam.recyclerviewjas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.item_city_layout.*
import kz.abudinislam.recyclerviewjas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    var listCity = citiesList()
    val adapter = CityAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.swipeRef.setOnRefreshListener {
            binding.swipeRef.isRefreshing = true
            Handler().postDelayed(
                {
                    initial()
                }, 5000
            )
        }

        binding.recyclerviewCity.adapter = adapter

        initial()

        adapter.onClickDelete = object : CityAdapter.OnClickDelete {

            override fun onDelete(item: CityModel) {
                delete(item)
            }
        }
    }

    private fun initial() {
        (binding.recyclerviewCity.adapter as CityAdapter).submitList(listCity)
        binding.swipeRef.isRefreshing = false
        binding.btnAdd.setOnClickListener {
        addCity()
        }
    }

    fun citiesList(): ArrayList<CityModel> {
        val cityList = ArrayList<CityModel>()

        val city = CityModel("Almaty", "Kazakhstan", R.drawable.almaty)
        cityList.add(city)

        val city2 = CityModel("Astana", "Kazakhstan", R.drawable.astana)
        cityList.add(city2)

        val city3 = CityModel("Paris", "France", R.drawable.paris)
        cityList.add(city3)

        val city4 = CityModel("London", "Great Britain", R.drawable.london)
        cityList.add(city4)

        val city5 = CityModel("Rome", "Italy", R.drawable.rome)
        cityList.add(city5)

        return cityList
    }

    private fun delete(item: CityModel) {
       listCity.remove(item)
        adapter.submitList(listCity)
        binding.recyclerviewCity.adapter=adapter

    }
    fun addCity(){
        listCity.add(CityModel(city, country))
        count++
        adapter.submitList(listCity)
        binding.recyclerviewCity.adapter=adapter
    }

    companion object {

        var count = 1
        var city = "City $count"
        var country = "Country $count"
    }
}