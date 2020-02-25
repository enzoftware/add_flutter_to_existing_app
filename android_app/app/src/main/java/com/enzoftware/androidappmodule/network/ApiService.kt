package com.enzoftware.androidappmodule.network

import com.enzoftware.androidappmodule.model.SimpsonsQuote
import retrofit2.http.GET


/**
 * Created by Enzo Lizama Paredes on 2020-02-16.
 * Contact: lizama.enzo@gmail.com
 */



interface ApiService {
    @GET("quotes?count=10")
//    @GET("xbaz0")
    suspend fun getSimpsonsQuotes(): List<SimpsonsQuote>
}