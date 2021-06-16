package com.dev.owlbot.home

import android.os.Bundle
import android.transition.Visibility
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.owlbot.R
import com.dev.owlbot.common.Utils
import com.dev.owlbot.home.adapter.DefinitionAdapter
import com.dev.owlbot.home.model.GetDescriptionRS
import com.dev.owlbot.home.presenter.GetDescriptionPresenter
import com.dev.owlbot.home.view.GetDescriptionView

class HomeActivity : AppCompatActivity(), GetDescriptionView {
    lateinit var rvDefinition: RecyclerView
    lateinit var actSearch: AutoCompleteTextView
    lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        actSearch = findViewById(R.id.actSearch)
        rvDefinition = findViewById(R.id.rvDefinitions)
        progressBar = findViewById(R.id.progressBar)
        actSearch.setOnTouchListener { v: View?, event: MotionEvent ->
            val DRAWABLE_LEFT = 0
            val DRAWABLE_TOP = 1
            val DRAWABLE_RIGHT = 2
            val DRAWABLE_BOTTOM = 3
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= actSearch.right - actSearch
                        .compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                ) {
                    // your action here
                    if (actSearch.text.toString().length > 1) {
                        getDescriptionApiCall(actSearch.text.toString())
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }
        actSearch.setOnEditorActionListener { textView: TextView?, i: Int, keyEvent: KeyEvent? ->
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                if (actSearch.text.toString().length > 1) {
                    getDescriptionApiCall(actSearch.text.toString())
                } else {
                    Toast.makeText(this, "Enter valid text.", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }
    }

    fun getDescriptionApiCall(query: String) {
        showProgressBar()
        Utils.hideSoftInput(this)
        if (rvDefinition.adapter != null) {
            (rvDefinition.adapter as DefinitionAdapter).clear()
        }
        GetDescriptionPresenter(this).getCityDistrictPinCodeOutPutCall(this, query)
    }

    override fun onGetDescriptionSuccess(description: GetDescriptionRS?) {
        val definitionAdapter = DefinitionAdapter(description?.definitions)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        rvDefinition.layoutManager = mLayoutManager
        rvDefinition.itemAnimator = DefaultItemAnimator()
        rvDefinition.addItemDecoration(
            DividerItemDecoration(
                rvDefinition.getContext(),
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