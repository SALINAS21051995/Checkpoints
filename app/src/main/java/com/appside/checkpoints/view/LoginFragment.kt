package com.appside.checkpoints.view

import android.content.Context
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.appside.checkpoints.R
import com.appside.checkpoints.contracts.LoginContract
import com.appside.checkpoints.viewModel.LoginViewModel
import com.appside.checkpoints.viewModel.LoginViewModelFactory
import com.appside.checkpoints.databinding.FragmentLoginBinding
import com.appside.checkpoints.presenter.LoginPresenter
import com.appside.checkpoints.util.AgreeDenyDialog
import com.appside.checkpoints.util.IDialogOptions

class LoginFragment : Fragment(), LoginContract.View, IDialogOptions {

    private var binding: FragmentLoginBinding? = null
    lateinit var viewModel: LoginViewModel
    private lateinit var viewModelFactory: LoginViewModelFactory
    private lateinit var presenter: LoginContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        presenter = LoginPresenter(this)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    private fun initComponents(){
        binding?.login = this
        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun getContext(): Context {
        return this.requireContext()
    }
    private fun onNavigateToView(layout: Int){
        when(layout){
            R.layout.fragment_create_admin -> {
                view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginFragmentToCreateAdminFragment())
            }
            R.layout.fragment_dashboard -> {
                view?.findNavController()?.navigate(LoginFragmentDirections.actionLoginFragmentToDashboardFragment())
            }
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_LONG).show()
    }

    override fun openOptionDialog() {
        val dialog = AgreeDenyDialog(getString(R.string.login_create_admin_text), this, null)
        this.parentFragmentManager.let { dialog.show(it, getString(R.string.dialog_title_text)) }
    }

    override fun onAgreeDeniedPositiveEvent(value: Any?) {
        onNavigateToView(R.layout.fragment_create_admin)
    }

    override fun onAgreeDeniedNegativeEvent(value: Any?) {

    }
    fun onLoginButtonAction(){
        if(this.presenter.validateInputs(viewModel.loginData)){
            //todo
        }
    }

}