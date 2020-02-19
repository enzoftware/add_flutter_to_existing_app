package com.enzoftware.androidappmodule.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enzoftware.androidappmodule.model.SimpsonsQuote
import com.enzoftware.androidappmodule.network.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


/**
 * Created by Enzo Lizama Paredes on 2020-02-19.
 * Contact: lizama.enzo@gmail.com
 */

class ListQuoteViewModel(private val apiService: ApiService) : ViewModel() {
    val quotes = MutableLiveData<List<SimpsonsQuote>>()
    val loading = MutableLiveData<Boolean>()

    fun fetchSimpsonsQuotes() {
        loading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            val data = apiService.getSimpsonsQuotes()
            withContext(Dispatchers.Main) {
                loading.value = false
                quotes.value = data
            }
        }
    }
}