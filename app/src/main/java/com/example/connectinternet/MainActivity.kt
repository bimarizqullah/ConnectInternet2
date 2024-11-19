package com.example.connectinternet

import android.os.Bundle
import Adapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.connectinternet.databinding.ActivityMainBinding
import com.example.connectinternet.json.Book
import com.example.connectinternet.json.House
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: Adapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Initialize RecyclerView and Adapter
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = Adapter()
        recyclerView.adapter = adapter

        // Fetch data from API
        fetchHouses()
        fetchBooks()
    }

    val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://potterapi-fedeperin.vercel.app/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    val api = retrofit.create(SimpleService::class.java)

    private fun fetchHouses() {
        api.getHouses().enqueue(object : Callback<List<House>> {
            override fun onResponse(call: Call<List<House>>, response: Response<List<House>>) {
                if (response.isSuccessful) {
                    val houses = response.body() ?: emptyList()
                    val combinedList = mutableListOf<Any>().apply {
                        add("Houses")
                        addAll(houses)
                    }
                    adapter.submitList(combinedList)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch houses", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<House>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fetchBooks() {
        api.getBooks().enqueue(object : Callback<List<Book>> {
            override fun onResponse(call: Call<List<Book>>, response: Response<List<Book>>) {
                if (response.isSuccessful) {
                    val books = response.body() ?: emptyList()
                    val combinedList = mutableListOf<Any>().apply {
                        add("Books")
                        addAll(books)
                    }
                    // Combine books with houses in the list
                    adapter.submitList(adapter.currentList + combinedList)
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch books", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Book>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
