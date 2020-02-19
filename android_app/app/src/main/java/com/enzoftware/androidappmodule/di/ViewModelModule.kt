package com.enzoftware.androidappmodule.di

import com.enzoftware.androidappmodule.ui.ListQuoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Enzo Lizama Paredes on 2020-02-16.
 * Contact: lizama.enzo@gmail.com
 */

val viewModelModule = module {
    viewModel { ListQuoteViewModel(get()) }
}