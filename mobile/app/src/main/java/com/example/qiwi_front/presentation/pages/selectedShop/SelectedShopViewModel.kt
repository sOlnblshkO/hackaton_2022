package com.example.qiwi_front.presentation.pages.selectedShop

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.data.services.code.CodeService
import com.example.domain.requests.code.getCode.GetCodeRequest
import com.example.domain.requests.code.sendCode.SendCodeRequest
import com.example.domain.responses.code.getCode.GetCodeResponse
import com.example.domain.responses.code.sendCode.SendCodeResponse
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.presentation.pages.selectedShop.contracts.LoadedShopData
import com.example.qiwi_front.utils.enums.StateEnum
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class SelectedShopViewModel @Inject constructor() : ViewModelBase() {

    @Inject
    lateinit var codeService: CodeService

    val loadedShopData = MutableLiveData<LoadedShopData>()
    val gotCodeResponse = MutableLiveData<GetCodeResponse>()
    val sendCodeResponse = MutableLiveData<SendCodeResponse>()

    fun loadData(selectedShopId: Int) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            delay(1000)
            loadedShopData.postValue(
                LoadedShopData(
                    selectedShopId, "Крутое заведение", "someUrl", "By koyash"
                )
            )
            state.postValue(StateEnum.Normal)
        }
    }

    fun createAndGetCode() {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            gotCodeResponse.postValue(
                codeService.getCode(
                    GetCodeRequest(
                        "78000000005", UUID.randomUUID().toString()
                    )
                )
            )
            state.postValue(StateEnum.Normal)
        }
    }

    fun sendCode(smsCode: String) {
        viewModelScope.launch {
            state.postValue(StateEnum.Loading)
            sendCodeResponse.postValue(
                codeService.sendCode(
                    SendCodeRequest(
                        gotCodeResponse.value!!.result?.requestId!!,
                        smsCode
                    )
                )
            )
            state.postValue(StateEnum.Normal)
        }
    }
}