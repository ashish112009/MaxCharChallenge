package com.codechallenge.maxcharoccurrence

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.codechallenge.maxcharoccurrence.databinding.ActivityMainBinding
import com.codechallenge.maxcharoccurrence.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        initObserver()
        initListener()
    }

    private fun initObserver() {
        homeViewModel.liveMaxOccurrenceValue.observe(this) {
            binding.tvCharOccurrence.text = it
        }
    }

    private fun initListener() {
        binding.btnEvaluate.setOnClickListener {
            homeViewModel.calculateMaxCharacterOccurrences(binding.tvEnterText.text.toString())
            hideKeyboard(it)
        }
    }

    private fun hideKeyboard(v: View) {
        val inputMethodManager: InputMethodManager =
            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(v.applicationWindowToken, 0)
    }
}