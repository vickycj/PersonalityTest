package com.vicky.apps.datapoints.ui.viewmodel

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.vicky.apps.datapoints.common.SchedulerProvider
import com.vicky.apps.datapoints.data.remote.Repository
import com.vicky.apps.datapoints.ui.model.QuestionTypeResponse

import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {


    @Mock
    lateinit var repository: Repository

    private val schedulerProvider = SchedulerProvider(Schedulers.trampoline(), Schedulers.trampoline())

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(repository, schedulerProvider)
    }

    @After
    fun tearDown() {
    }


    @Test
    fun getDataFromRemote() {

        Mockito.`when`(repository.getDataFromApi()).thenReturn(Single.just(getObject()))

        val testObserver = TestObserver<QuestionTypeResponse>()

        viewModel.generateApiCall()
            .subscribe(testObserver)

        testObserver.assertNoErrors()
        testObserver.assertValue { responseData -> responseData.questions!!.isNotEmpty() }
    }



    @Test
    fun testFilterData(){
        viewModel.filterData("hard_fact")

       viewModel.getDataList().forEach {
           assert(it.category != "introversion" && it.category != "lifestyle" && it.category != "passion" )
       }
    }

    @Test
    fun testResetFilter(){
        viewModel.resetFilter()

        viewModel.getDataList().forEach {
            assert(it.category == "introversion" || it.category == "lifestyle" || it.category == "passion" || it.category == "hard_fact")
        }
    }


    private fun getObject():QuestionTypeResponse{
        val token = object : TypeToken<QuestionTypeResponse>() {

        }
        val jsonData = "{\n" +
                "  \"categories\": [\n" +
                "    \"hard_fact\",\n" +
                "    \"lifestyle\",\n" +
                "    \"introversion\",\n" +
                "    \"passion\"\n" +
                "  ],\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"question\": \"What is your gender?\",\n" +
                "      \"category\": \"hard_fact\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"male\",\n" +
                "          \"female\",\n" +
                "          \"other\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How important is the gender of your partner?\",\n" +
                "      \"category\": \"hard_fact\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"not important\",\n" +
                "          \"important\",\n" +
                "          \"very important\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How important is the age of your partner to you?\",\n" +
                "      \"category\": \"hard_fact\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice_conditional\",\n" +
                "        \"options\": [\n" +
                "          \"not important\",\n" +
                "          \"important\",\n" +
                "          \"very important\"\n" +
                "        ],\n" +
                "        \"condition\": {\n" +
                "          \"predicate\": {\n" +
                "            \"exactEquals\": [\n" +
                "              \"${"ds"}\",\n" +
                "              \"very important\"\n" +
                "            ]\n" +
                "          },\n" +
                "          \"if_positive\": {\n" +
                "            \"question\": \"What age should your potential partner be?\",\n" +
                "            \"category\": \"hard_fact\",\n" +
                "            \"question_type\": {\n" +
                "              \"type\": \"number_range\",\n" +
                "              \"range\": {\n" +
                "                \"from\": 18,\n" +
                "                \"to\": 140\n" +
                "              }\n" +
                "            }\n" +
                "          }\n" +
                "        }\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Do any children under the age of 18 live with you?\",\n" +
                "      \"category\": \"hard_fact\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"yes\",\n" +
                "          \"sometimes\",\n" +
                "          \"no\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How should your potential partner respond to this question?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"yes\",\n" +
                "          \"sometimes\",\n" +
                "          \"no\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Could you imagine having children with your potential partner?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"yes\",\n" +
                "          \"maybe\",\n" +
                "          \"no\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How should your potential partner respond to this question?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"yes\",\n" +
                "          \"maybe\",\n" +
                "          \"no\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"What is your marital status?\",\n" +
                "      \"category\": \"hard_fact\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"never married\",\n" +
                "          \"separated\",\n" +
                "          \"divorced\",\n" +
                "          \"widowed\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How often do your drink alcohol?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"never\",\n" +
                "          \"once or twice a year\",\n" +
                "          \"once or twice a month\",\n" +
                "          \"once or twice a week\",\n" +
                "          \"I'm drinking my 3rd mojito now, and it's only 11am\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How often do you smoke?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"never\",\n" +
                "          \"once or twice a year\",\n" +
                "          \"socially\",\n" +
                "          \"frequently\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"What is your attitude towards drugs?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"I'm completely opposed\",\n" +
                "          \"I've been know to dabble\",\n" +
                "          \"drugs enrich my life\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"You are looking for...\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"friendship\",\n" +
                "          \"a hot date\",\n" +
                "          \"an affair\",\n" +
                "          \"a short-term relationship\",\n" +
                "          \"a long-term relationship\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Would you like to get married?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"yes\",\n" +
                "          \"probably\",\n" +
                "          \"eventually\",\n" +
                "          \"no\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"What is your ideal living arrangement?\",\n" +
                "      \"category\": \"lifestyle\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"cohabitation\",\n" +
                "          \"separate flat / room in the same building\",\n" +
                "          \"separate flats in the same area\",\n" +
                "          \"weekend-relationship\",\n" +
                "          \"long distance relationship\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Do you enjoy spending time alone?\",\n" +
                "      \"category\": \"introversion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"most of the time\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"not at all\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"When you're alone, do you get lonely quickly?\",\n" +
                "      \"category\": \"introversion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"most of the time\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"not at all\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Do you enjoy going on holiday by yourself?\",\n" +
                "      \"category\": \"introversion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"most of the time\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"not at all\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"I consciously take \\\"me time\\\"\",\n" +
                "      \"category\": \"introversion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"most of the time\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"not at all\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Should one keep little secrets from one's partner?\",\n" +
                "      \"category\": \"introversion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"most of the time\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"not at all\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How often do you think about sex?\",\n" +
                "      \"category\": \"passion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"a few times a day\",\n" +
                "          \"daily\",\n" +
                "          \"a few times a week\",\n" +
                "          \"a few times a month\",\n" +
                "          \"rarely\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"If you were alone on a desert island, how long would you last before pleasuring yourself?\",\n" +
                "      \"category\": \"passion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"less than a day\",\n" +
                "          \"one day\",\n" +
                "          \"one week\",\n" +
                "          \"one month\",\n" +
                "          \"I'd never do something like that\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"How often would you like to have sex with your prospective partner?\",\n" +
                "      \"category\": \"passion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"every day\",\n" +
                "          \"a few times a week\",\n" +
                "          \"once a week\",\n" +
                "          \"every two weeks\",\n" +
                "          \"infrequently\",\n" +
                "          \"rarely\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"Do you like trying out new things in bed and experimenting?\",\n" +
                "      \"category\": \"passion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"Yes, definitely!\",\n" +
                "          \"Now and then - why not?\",\n" +
                "          \"I'd give it a try\",\n" +
                "          \"I don't know\",\n" +
                "          \"Absolutely not\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"I can enjoy sex without love\",\n" +
                "      \"category\": \"passion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"always\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"never\"\n" +
                "        ]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"question\": \"For me, a stable relationship is a prerequisite for really good sex\",\n" +
                "      \"category\": \"passion\",\n" +
                "      \"question_type\": {\n" +
                "        \"type\": \"single_choice\",\n" +
                "        \"options\": [\n" +
                "          \"always\",\n" +
                "          \"often\",\n" +
                "          \"sometimes\",\n" +
                "          \"rarely\",\n" +
                "          \"never\"\n" +
                "        ]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}"
        val gson = Gson()
        return gson.fromJson(jsonData,token.type)

    }








}