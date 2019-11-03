package com.vicky.apps.datapoints.ui.view


import com.vicky.apps.datapoints.R
import kotlinx.android.synthetic.main.activity_main.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.Robolectric


@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var mainActivity: MainActivity

    @Before
    fun setup() {
        mainActivity = Robolectric.setupActivity(MainActivity::class.java)
    }

    @Test
    fun clickFilter_shouldChangeCount() {


        mainActivity.chipGroupText.clearCheck()
        mainActivity.chipGroupText.check(R.id.chip1)

        val count = mainActivity.questionCardRecyclerView.adapter!!.itemCount

        assert(count < 25)

    }


    @Test
    fun clickFilter_passion_check_the_card_count() {

        mainActivity.chipGroupText.clearCheck()
        mainActivity.chipGroupText.check(R.id.chip3)

        val count = mainActivity.questionCardRecyclerView.adapter!!.itemCount

        assert(count < 10)

    }

    @Test
    fun reset_all_filter_check_count() {


        mainActivity.chipGroupText.clearCheck()

        val count = mainActivity.questionCardRecyclerView.adapter!!.itemCount

        assert(count > 10)
    }

}