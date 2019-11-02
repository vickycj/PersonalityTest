package com.vicky.apps.datapoints.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.QuestionTypeResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy


class MainViewModel(private val repository: Repository,
                    private val schedulerProvider: SchedulerProvider
):ViewModel() {




    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getSubscription():MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable



    private var ascendingVal:Boolean = false

    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }



    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy ( onSuccess = {
           Log.d("name",it.questions?.size.toString())
        }, onError = {
            Log.d("valuessss",it.message)
        } ))


    }
    fun generateApiCall():Single<QuestionTypeResponse>{
        return repository.getDataFromApi()
            .compose(schedulerProvider.getSchedulersForSingle())
    }







}