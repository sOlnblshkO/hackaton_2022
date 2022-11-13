package com.example.qiwi_front.base.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.qiwi_front.R
import com.example.qiwi_front.base.viewModel.ViewModelBase
import com.example.qiwi_front.databinding.StatesBinding

abstract class FragmentBase<VBinding : ViewBinding, ViewModel : ViewModelBase> :
    StateObserverSetter() {
    private val fragmentContainerId: Int = R.id.mainFragmentContainer

    protected lateinit var viewModel: ViewModel
    protected abstract fun getViewModelClass(): Class<ViewModel>

    protected lateinit var binding: VBinding
    protected abstract fun getViewBinding(): VBinding

    protected abstract fun getStateBinding(): StatesBinding?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
        observeData()
    }

    open fun setUpViews() {}

    @SuppressLint("FragmentLiveDataObserve", "HardwareIds")
    open fun observeData() {
        viewModel.state.observe(this) {
            if (statesBinding != null)
                setState(it)
        }

        viewModel.someMessage.observe(this) {
            if (it.isNullOrEmpty())
                return@observe
//            questionDialogBuilder.showDialog(requireContext(), it)
//            questionDialogBuilder.binding.exitButtonsLayout.dialogCancelButton.visibility = View.GONE
//            questionDialogBuilder.binding.exitButtonsLayout.dialogOkButton.setOnClickListener {
//                questionDialogBuilder.dismiss()
//            }
            viewModel.someMessage.postValue(null)
        }

    }

    fun replaceFragment(newFragment: Fragment) {
        parentFragmentManager
            .apply {
                fragments.forEach {
                    beginTransaction().remove(it).commit()
                }
                beginTransaction()
                    .replace(fragmentContainerId, newFragment)
                    .commit()
            }
    }

    fun addFragment(newFragment: Fragment) {
        parentFragmentManager
            .beginTransaction()
            .add(fragmentContainerId, newFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(newFragment::class.java.name)
            .commit()
    }

    fun goBack() {
        activity?.supportFragmentManager?.popBackStack()
    }

    private fun init() {
        binding = getViewBinding()
        statesBinding = getStateBinding()
        viewModel = ViewModelProvider(this).get(getViewModelClass())
    }

    fun setNotificationBarColor(colorInt: Int){
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = colorInt
    }
}