package com.enzoftware.androidappmodule.di

import com.enzoftware.androidappmodule.network.ApiService
import org.koin.dsl.module
import retrofit2.Retrofit


/**
 * Created by Enzo Lizama Paredes on 2020-02-16.
 * Contact: lizama.enzo@gmail.com
 */


val apiModule = module {
    single(createdAtStart = true) { get<Retrofit>().create(ApiService::class.java) }
}