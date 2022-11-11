package com.example.qiwi_front.base.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qiwi_front.utils.enums.StateEnum

open class ViewModelBase : ViewModel() {
    open var someMessage: MutableLiveData<String> = MutableLiveData("")
    open var state: MutableLiveData<StateEnum> = MutableLiveData(StateEnum.Normal)
}