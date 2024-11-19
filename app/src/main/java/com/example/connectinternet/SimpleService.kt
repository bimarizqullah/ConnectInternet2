package com.example.connectinternet

import com.example.connectinternet.json.Book
import com.example.connectinternet.json.House
import com.example.connectinternet.json.Artists
import retrofit2.http.GET

import retrofit2.Call

interface SimpleService {
    //houses
    @GET("en/houses")
    fun getHouses(): Call<List<House>>

    //books
    @GET("en/books")
    fun getBooks(): Call<List<Book>>
}
