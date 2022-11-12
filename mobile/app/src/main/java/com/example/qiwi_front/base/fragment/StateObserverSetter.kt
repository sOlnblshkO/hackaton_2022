package com.example.qiwi_front.base.fragment

import android.view.View
import androidx.fragment.app.Fragment
import com.example.qiwi_front.databinding.StatesBinding
import com.example.qiwi_front.utils.enums.StateEnum

open class StateObserverSetter : Fragment() {

    protected lateinit var statesBinding: StatesBinding

    var lastState: View? = null

    private fun setStatesBindingIsFocusableIsClickable(newStates: Boolean){
        statesBinding.root.isClickable = newStates
        statesBinding.root.isFocusable = newStates
    }

    fun setState(
        stateEnum: StateEnum
    ) {
        val lastFragment = requireActivity().supportFragmentManager.fragments.last()
        if (lastFragment.javaClass.name.contains("Dialog") || lastFragment.javaClass.name.contains("Builder"))
            return

        if (lastState != null) {
            lastState!!.visibility = View.GONE
            statesBinding.tryAgainButton.visibility = View.GONE
            setStatesBindingIsFocusableIsClickable(false)
        }
        when (stateEnum) {
            StateEnum.Normal ->{
                statesBinding.tryAgainButton.visibility = View.GONE
                setStatesBindingIsFocusableIsClickable(false)
            }
            StateEnum.Loading -> {
                statesBinding.stateLoading.root.visibility = View.VISIBLE
                statesBinding.tryAgainButton.visibility = View.GONE
                lastState = statesBinding.stateLoading.root
                setStatesBindingIsFocusableIsClickable(true)
            }
            StateEnum.Error -> {
                statesBinding.stateUnexpectedError.root.visibility = View.VISIBLE
                statesBinding.tryAgainButton.visibility = View.VISIBLE
                lastState = statesBinding.stateUnexpectedError.root
                setStatesBindingIsFocusableIsClickable(true)
            }
        }
    }
}