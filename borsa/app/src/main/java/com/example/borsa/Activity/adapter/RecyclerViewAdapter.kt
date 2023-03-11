package com.example.borsa.Activity.adapter


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.borsa.Activity.model.CryptoModel
import com.example.borsa.R
import com.example.borsa.databinding.RowLayoutCryptoBinding



    class RecyclerViewAdapter (private val cryptoList: ArrayList<CryptoModel>,private val listener :Listener ): RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {


        interface Listener {
            fun onItemClick(cryptoModel: CryptoModel)
        }
        private val colors: Array<String> = arrayOf("#485b1f","#1e5b92","#c91414","#322697","#ecd4de")


        class RowHolder(view :View) : RecyclerView.ViewHolder(view) {
            private lateinit var binding : RowLayoutCryptoBinding


            fun bind(cryptoModel: CryptoModel,colors : Array<String>,position: Int,listener : Listener){

                itemView.setOnClickListener{
                    listener.onItemClick(cryptoModel)
                }
                itemView.setBackgroundColor(Color.parseColor(colors[position % 8]))
                binding.textName.text = cryptoModel.currency
                binding.textprice.text = cryptoModel.price
        }
    }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_crypto,parent,false)
            return RowHolder(view)
    }

        override fun getItemCount(): Int {
            return cryptoList.count()
    }

        override fun onBindViewHolder(holder: RowHolder, position: Int) {
            holder.bind(cryptoList[position],colors,position,listener)
    }

}