package com.example.borsa.Activity.service

import com.example.borsa.Activity.model.CryptoModel
import retrofit2.Call
import retrofit2.http.GET

interface CryptoAPI {

    //base Url : https://raw.githubusercontent.com/
    //atilsamancioglu/K21-JSONDataSet/master/crypto.json
    //Get,Post,Update,Delete


    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    fun getData():Call<List<CryptoModel>>


}