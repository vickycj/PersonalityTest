package com.vicky.apps.datapoints.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.components.Options
import com.vicky.apps.datapoints.components.model.OptionsData
import com.vicky.apps.datapoints.components.model.QuestionCardData
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.QuestionTypeResponse
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.util.Locale.filter


class MainViewModel(
    private val repository: Repository,
    private val schedulerProvider: SchedulerProvider
) : ViewModel() {


    private var mainList:MutableList<QuestionCardData> = ArrayList()

    private var questionsList: MutableList<QuestionCardData> = ArrayList()

    private val response: MutableLiveData<Boolean> = MutableLiveData()

    fun getDataList():List<QuestionCardData> = questionsList

    fun getSubscription(): MutableLiveData<Boolean> = response

    private lateinit var compositeDisposable: CompositeDisposable


    fun setCompositeData(compositeDisposable: CompositeDisposable) {
        this.compositeDisposable = compositeDisposable
    }


    fun getDataFromRemote() {

        compositeDisposable.add(generateApiCall().subscribeBy(onSuccess = {
            restructureResponse(it)
        }, onError = {
            response.postValue(false)
        }))


    }


    private fun restructureResponse(questionTypeResponse: QuestionTypeResponse) {
        questionsList.clear()
        questionTypeResponse.questions?.forEach {
            val options: MutableList<OptionsData> = ArrayList()
            it?.questionType?.options?.forEach { name ->
                options.add(OptionsData(name!!.capitalize(), false))
            }
            questionsList.add(
                QuestionCardData(
                    it?.question!!,
                    Options.SINGLE_CHOICE,
                    options,
                    it.category!!
                )
            )
        }

        mainList = questionsList
        response.postValue(true)

    }

    fun generateApiCall(): Single<QuestionTypeResponse> {
        return repository.getDataFromApi()
            .compose(schedulerProvider.getSchedulersForSingle())
    }

    fun filterData(tag: String?) {
        questionsList = mainList.filter {
            it.category == tag
        }.toMutableList()
    }

    fun resetFilter() {
        questionsList = mainList.toMutableList()
    }


}