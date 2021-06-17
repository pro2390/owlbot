package com.dev.owlbot.home

import android.os.Bundle
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.owlbot.R
import com.dev.owlbot.common.Utils
import com.dev.owlbot.home.adapter.DefinitionAdapter
import com.dev.owlbot.home.model.GetDescriptionRS
import com.dev.owlbot.home.model.SearchQueryTextValidator
import com.dev.owlbot.home.presenter.GetDescriptionPresenter
import com.dev.owlbot.home.view.GetDescriptionView

class HomeActivity : AppCompatActivity(), GetDescriptionView {
    private lateinit var rvDefinition: RecyclerView
    private lateinit var actSearch: AutoCompleteTextView
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        actSearch = findViewById(R.id.actSearch)
        rvDefinition = findViewById(R.id.rvDefinitions)
        progressBar = findViewById(R.id.progressBar)
        actListener()
    }

    //Right drawable click listener for AutoCompleteTextView
    private fun actListener() {
        actSearch.setOnTouchListener { _: View?, event: MotionEvent ->
            val drawableRight = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= actSearch.right - actSearch
                        .compoundDrawables[drawableRight].bounds.width()
                ) {
                    searchClick()
                    return@setOnTouchListener true
                }
            }
            false
        }
        actSearch.setOnEditorActionListener { _: TextView?, i: Int, _: KeyEvent? ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                searchClick()
            }
            false
        }
    }

    //Click action check if user input is valid
    private fun searchClick() {
        val validator = SearchQueryTextValidator()
        if (validator.validateQuery(actSearch.text.toString())) {
            getDescriptionApiCall(actSearch.text.toString())
        } else {
            Toast.makeText(this, getString(R.string.valid_input), Toast.LENGTH_SHORT)
                .show()
        }
    }

    //Method for api call to get definition of word entered
    private fun getDescriptionApiCall(query: String) {
        showProgressBar()
        Utils.hideSoftInput(this)
        if (rvDefinition.adapter != null) {
            (rvDefinition.adapter as DefinitionAdapter).clear()
        }
        GetDescriptionPresenter(this).getCityDistrictPinCodeOutPutCall(this, query)
    }

    //Success callback for api call
    override fun onGetDescriptionSuccess(description: GetDescriptionRS?) {
        val definitionAdapter = DefinitionAdapter(description?.definitions)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rvDefinition.layoutManager = mLayoutManager
        rvDefinition.itemAnimator = DefaultItemAnimator()
        rvDefinition.addItemDecoration(
            DividerItemDecoration(
                rvDefinition.context,
                DividerItemDecoration.VERTICAL
            )
        )
        rvDefinition.adapter = definitionAdapter
        hideProgressBar()
    }

    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }

    override fun showNoNetworkAlert() {
        hideProgressBar()
        Toast.makeText(this, getString(R.string.error_msg_internet), Toast.LENGTH_SHORT).show()
    }

    override fun showError(errorMessage: String?) {
        hideProgressBar()
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()

    }

    override fun showServerUnderMaintenance() {
        hideProgressBar()
        Toast.makeText(this, getString(R.string.error_msg_servererror), Toast.LENGTH_SHORT).show()
    }
}