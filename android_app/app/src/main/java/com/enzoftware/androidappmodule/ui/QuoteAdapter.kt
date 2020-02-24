package com.enzoftware.androidappmodule.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.enzoftware.androidappmodule.R
import com.enzoftware.androidappmodule.databinding.QuoteItemBinding
import com.enzoftware.androidappmodule.model.SimpsonsQuote


/**
 * Created by Enzo Lizama Paredes on 2020-02-19.
 * Contact: lizama.enzo@gmail.com
 */

class QuoteAdapter(val adapterOnClick: QuoteAdapterOnClick) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>(){

    private val quotes = ArrayList<SimpsonsQuote>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quote_item, parent, false)
        return QuoteViewHolder(itemView, parent.context)
    }

    override fun getItemCount(): Int = quotes.size

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bindQuote(quotes[position])
    }

    fun addQuotes(listQuotes: List<SimpsonsQuote>) {
        this.quotes.addAll(listQuotes)
        notifyDataSetChanged()
    }

    inner class QuoteViewHolder(itemView: View, private val context: Context): RecyclerView.ViewHolder(itemView){

        fun bindQuote(quote: SimpsonsQuote){
            val binding = DataBindingUtil.bind<QuoteItemBinding>(itemView)
            binding?.quote = quote
            binding?.quoteCardView?.setOnClickListener {
                adapterOnClick.onClick(quote)
            }
        }

    }
}