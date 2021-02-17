package com.example.changemakers.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.changemakers.network.CmApi
import com.example.changemakers.network.CmProperty
import kotlinx.coroutines.launch
import com.example.changemakers.network.CmApiFilter

enum class CmApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _navigateToSelectedProperty = MutableLiveData<CmProperty?>()
    val navigateToSelectedProperty: MutableLiveData<CmProperty?>
        get() = _navigateToSelectedProperty

    private val _status = MutableLiveData<CmApiStatus>()

    val status: LiveData<CmApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<CmProperty>>()

    val properties: LiveData<List<CmProperty>>
        get() = _properties

    init {
        getCmProperties(CmApiFilter.SHOW_ALL)
    }

    private fun getCmProperties(filter: CmApiFilter) {

        viewModelScope.launch {
            _status.value = CmApiStatus.LOADING
            try {
                _properties.value = CmApi.retrofitService.getProperties(filter.value)
                _status.value = CmApiStatus.DONE
            } catch (e: Exception) {
                _status.value = CmApiStatus.ERROR
                _properties.value = ArrayList()            }
        }
    }

    fun updateFilter(filter: CmApiFilter) {
        getCmProperties(filter)
    }

    fun displayPropertyDetails(CmProperty: CmProperty) {
        _navigateToSelectedProperty.value = CmProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

}