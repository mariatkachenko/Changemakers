package com.example.changemakers.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.changemakers.network.CmProperty
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.changemakers.R

class DetailViewModel( cmProperty: CmProperty,
                       app: Application) : AndroidViewModel(app) {

    private val _selectedProperty = MutableLiveData<CmProperty>()
    val selectedProperty: LiveData<CmProperty>
        get() = _selectedProperty
    init {
        _selectedProperty.value = cmProperty
    }
    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when (it.isFound) {
                true -> R.string.display_price
                false -> R.string.display_price
            }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                when (it.isFound) {
                    true -> R.string.type_found
                    false -> R.string.type_sale
                }))
    }

}