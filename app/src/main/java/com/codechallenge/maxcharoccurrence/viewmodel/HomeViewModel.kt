package com.codechallenge.maxcharoccurrence.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codechallenge.maxcharoccurrence.usecase.CalculateMaxCharOccurrenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val calculateMaxCharOccurrenceUseCase: CalculateMaxCharOccurrenceUseCase
) : ViewModel() {
    private val _liveMaxOccurrenceValue = MutableLiveData<String>()
    val liveMaxOccurrenceValue = _liveMaxOccurrenceValue

    fun calculateMaxCharacterOccurrences(text: String) {
        viewModelScope.launch {
            _liveMaxOccurrenceValue.value = calculateMaxCharOccurrenceUseCase.run(text)
        }
    }
}