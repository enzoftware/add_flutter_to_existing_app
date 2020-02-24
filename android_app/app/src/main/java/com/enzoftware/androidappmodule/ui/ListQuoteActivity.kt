package com.enzoftware.androidappmodule.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.enzoftware.androidappmodule.R
import com.enzoftware.androidappmodule.databinding.ContentMainBinding
import com.enzoftware.androidappmodule.model.SimpsonsQuote
import com.google.gson.Gson
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.StringCodec
import org.koin.android.viewmodel.ext.android.viewModel


class ListQuoteActivity : AppCompatActivity(), QuoteAdapterOnClick {

    companion object {
        // Define a tag String to represent the FlutterFragment within this
        // Activity's FragmentManager. This value can be whatever you'd like.
        private const val TAG_FLUTTER_FRAGMENT = "flutter_fragment"
        private val CHANNEL = "flutter.dev/add2toApp"

    }

    lateinit var flutterEngine : FlutterEngine
    private val quoteViewModel : ListQuoteViewModel by viewModel()
    private lateinit var adapter: QuoteAdapter
    private lateinit var mBinding: ContentMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



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


        mBinding = DataBindingUtil.setContentView(this, R.layout.content_main)
        adapter = QuoteAdapter(this)
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


    }

    override fun onClick(quote: SimpsonsQuote) {
        startActivity(
            FlutterActivity
                .withCachedEngine("my_engine_id")
                .build(this)
        )

        val mc = BasicMessageChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL, StringCodec.INSTANCE)
        mc.send(Gson().toJson(quote))


    }


}
