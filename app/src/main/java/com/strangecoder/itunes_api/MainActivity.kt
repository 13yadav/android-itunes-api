package com.strangecoder.itunes_api

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.strangecoder.itunes_api.databinding.ActivityMainBinding
import com.strangecoder.itunes_api.ui.MainViewModel
import com.strangecoder.itunes_api.ui.SearchListAdapter
import com.strangecoder.itunes_api.ui.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewModelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.setNetworkTextVisibility(binding.networkText)

        val manager = GridLayoutManager(this, 3)
        binding.searchResultList.layoutManager = manager

        binding.searchButton.setOnClickListener {
            val searchTerm = binding.searchText.text.toString().trim()
            viewModel.searchForResults(searchTerm)
        }

        val searchListAdapter = SearchListAdapter()
        binding.searchResultList.adapter = searchListAdapter

        viewModel.searchResults.observe(this, {
            it?.let {
                binding.networkText.visibility = View.GONE
                searchListAdapter.submitList(it)
            }
        })
    }
}