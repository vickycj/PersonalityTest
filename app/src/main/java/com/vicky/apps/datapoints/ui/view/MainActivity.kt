package com.vicky.apps.datapoints.ui.view

import android.os.Bundle

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import androidx.recyclerview.widget.RecyclerView
import com.vicky.apps.datapoints.base.BaseActivity
import com.vicky.apps.datapoints.common.ViewModelProviderFactory
import com.vicky.apps.datapoints.ui.adapter.DataAdapter

import com.vicky.apps.datapoints.ui.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.chip.Chip

import com.google.android.material.chip.ChipGroup


class MainActivity : BaseActivity() {


    @Inject
    lateinit var factory: ViewModelProviderFactory

    private lateinit var viewModel: MainViewModel

    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vicky.apps.datapoints.R.layout.activity_main)
        inilializingRecyclerView()
        initializeValues()


        viewModel.getDataFromRemote()
    }

    private fun inilializingRecyclerView() {

        recyclerView = questionCardRecyclerView

        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        adapter = DataAdapter(ArrayList())

        recyclerView.adapter = adapter
    }

    private fun initializeValues() {

        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

        viewModel.setCompositeData(compositeDisposable)

        viewModel.getSubscription().observe(this, Observer {
            if (it) {
                successCallback()
            } else {
                failureCallback()
            }
        })


        chipGroupText.setOnCheckedChangeListener(ChipGroup.OnCheckedChangeListener { chipGroup, i ->
            val chip = chipGroup.findViewById<Chip>(i)
            if (chip != null) {
                viewModel.filterData(chip.tag.toString())
                updateData()
            } else {
                viewModel.resetFilter()
                updateData()
            }

        })
    }


    private fun successCallback() {
        updateData()
    }

    private fun updateData() {
        adapter.updateData(viewModel.getDataList())
    }


    private fun failureCallback() {
        Toast.makeText(this, "API failed", Toast.LENGTH_LONG).show()
    }


}
