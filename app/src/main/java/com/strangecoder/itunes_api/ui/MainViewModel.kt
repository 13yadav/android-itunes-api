package com.strangecoder.itunes_api.ui

import android.app.Application
import android.view.View
import android.widget.TextView
import androidx.lifecycle.*
import com.strange.coder.videosharingapp.network.util.NetworkHelper
import com.strangecoder.itunes_api.R
import com.strangecoder.itunes_api.db.ResultDatabase
import com.strangecoder.itunes_api.domain.SongItem
import com.strangecoder.itunes_api.domain.mapDatabaseToDomain
import com.strangecoder.itunes_api.domain.mapNetworkToDatabase
import com.strangecoder.itunes_api.domain.mapNetworkToDomain
import com.strangecoder.itunes_api.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val database = ResultDatabase.getInstance(application)
    private val repository = Repository(database)
    private val networkHelper = NetworkHelper(application)
    private val resources = application.resources

    private val _searchResults = MutableLiveData<List<SongItem>>()
    val searchResults: LiveData<List<SongItem>>
        get() = _searchResults

    fun searchForResults(searchTerm: String) {
        if (networkHelper.isNetworkConnected()) {
            getSearchResults(searchTerm)
        } else {
            getResultsFromDB(searchTerm)
        }
    }

    private fun getSearchResults(searchTerm: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getSearchResults(searchTerm).await()
        withContext(Dispatchers.Main) {
            _searchResults.value = mapNetworkToDomain(result.results)
        }
        repository.insertAllResults(mapNetworkToDatabase(result.results))
    }

    private fun getResultsFromDB(searchTerm: String) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getLocalResults(searchTerm)
        withContext(Dispatchers.Main) {
            val data = mapDatabaseToDomain(result)
            _searchResults.value = data
        }
    }

    fun setNetworkTextVisibility(view: TextView) {
        if (networkHelper.isNetworkConnected()) {
            view.visibility = View.VISIBLE
            view.text = resources.getString(R.string.network_available)
        } else {
            view.visibility = View.VISIBLE
            view.text = resources.getString(R.string.no_network)
        }
    }

}

class ViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}