package com.enzoftware.androidappmodule.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enzoftware.androidappmodule.R
import com.enzoftware.androidappmodule.databinding.ContentMainBinding
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class ListQuoteActivity : AppCompatActivity() {

    lateinit var flutterEngine : FlutterEngine
    private val quoteViewModel : ListQuoteViewModel by viewModel()
    private lateinit var adapter: QuoteAdapter
    private lateinit var mBinding: ContentMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        mBinding = DataBindingUtil.setContentView(this, R.layout.content_main)
        adapter = QuoteAdapter()
        mBinding.recyclerViewQuotes.layoutManager = LinearLayoutManager(this)
        mBinding.recyclerViewQuotes.adapter = adapter
        quoteViewModel.fetchSimpsonsQuotes()
        quoteViewModel.quotes.observe(this, Observer {
            adapter.addQuotes(it)
        })
        quoteViewModel.loading.observe(this, Observer {
            if(it){
                mBinding.pbLoading.visibility = View.VISIBLE
            } else {
                mBinding.pbLoading.visibility = View.INVISIBLE
            }
        })


        // Instantiate a FlutterEngine.
        flutterEngine = FlutterEngine(this)

        // Start executing Dart code to pre-warm the FlutterEngine.
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        // Cache the FlutterEngine to be used by FlutterActivity.
        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)
    }


}
