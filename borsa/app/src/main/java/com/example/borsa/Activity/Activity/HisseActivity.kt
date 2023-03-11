package com.example.borsa.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.borsa.Activity.adapter.RecyclerViewAdapter
import com.example.borsa.Activity.model.CryptoModel
import com.example.borsa.Activity.service.CryptoAPI
import com.example.borsa.R
import com.example.borsa.databinding.ActivityHisseBinding
import com.example.borsa.databinding.ActivityKayitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


 class hisseActivity : AppCompatActivity(), RecyclerViewAdapter.Listener {
    private val Base_Url ="https://raw.githubusercontent.com/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private lateinit var binding: ActivityHisseBinding
    private  lateinit var recyclerViewAdapter: RecyclerViewAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHisseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //recyclerView

        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadData()

    }

    private fun loadData(){
        val retrofit =Retrofit.Builder().baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object : Callback<List<CryptoModel>> {


            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels =  ArrayList(it)


                        cryptoModels?.let {
                            recyclerViewAdapter = RecyclerViewAdapter(it,this@hisseActivity)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }




                    /* for (cryptoModel : CryptoModel in cryptoModels!!){
                            println(cryptoModel.currency)
                            println(cryptoModel.price)
                        }*/
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

     override fun onItemClick(cryptoModel: CryptoModel) {
         Toast.makeText(this,"Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
     }


 }