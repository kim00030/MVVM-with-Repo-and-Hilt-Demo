package com.dan.mvvmwithrepoandhiltdemo.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.dan.mvvmwithrepoandhiltdemo.R
import com.dan.mvvmwithrepoandhiltdemo.databinding.ActivityMainBinding
import com.dan.mvvmwithrepoandhiltdemo.model.Blog
import com.dan.mvvmwithrepoandhiltdemo.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val DEBUG_TAG = "myDebug"
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetVlogEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, { dataState ->

            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    showProgressbar(isDisplaying = false)
                    appendBlogTitles(dataState.data)

                }
                is DataState.Error -> {
                    showProgressbar(isDisplaying = false)
                    showError(dataState.exception.message)

                }
                is DataState.Loading -> {
                    showProgressbar(isDisplaying = true)
                }
            }
        })
    }

    private fun showError(message: String?) {
        if (!message.isNullOrBlank()) {
            binding.textView.text = message
        } else {
            binding.textView.text = getString(R.string.msg_unknown_error)
        }
    }

    private fun showProgressbar(isDisplaying: Boolean) {
        binding.progressBar.visibility = if (isDisplaying) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitles(blogs: List<Blog>) {

        val sb = StringBuilder()
        blogs.forEach {
            sb.append(it.title).append("\n")
        }
        binding.textView.text = sb.toString()

    }
}