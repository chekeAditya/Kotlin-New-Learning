package com.aditya.jetpackwithflows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val countDownFlow = flow<Int> {
        val startingValue = 10 //10 sec starting time in sec
        var currentValue = startingValue
        emit(startingValue) // here we are returning starting value first
        while (currentValue > 0) {
            delay(1000L)
            currentValue--
            emit(currentValue) //used to return the change
        }
    }

    init {
        collectFlow()
    }

    private fun collectFlow() {
        viewModelScope.launch {
            /* You can use this when you have to show the latest emission
            countDownFlow.collectLatest {   time ->
                delay(1500L)
                println("The current time is $time")
            }
             */
            countDownFlow.collect {   time ->
                delay(1500L)
                println("The current time is $time")
            }
        }
    }
}

/*
 * Collect -> Executed every single emission
 * CollectLatest -> Executed every single emission if collect block take some and not yet finish yet and we get the new emission then the block will be canceled and will fetch the latest update.
 *               -> It will get the latest emission and will emit it
 */