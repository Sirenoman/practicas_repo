package com.jonathancabrera.dummydictionary.ui.word.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.jonathancabrera.dummydictionary.DummyDictionaryApplication
import com.jonathancabrera.dummydictionary.MainActivity
import com.jonathancabrera.dummydictionary.R
import com.jonathancabrera.dummydictionary.databinding.ActivityLoginBinding
import com.jonathancabrera.dummydictionary.viewmodel.ViewModelFactory

class LoginActivity: AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    val app by lazy {
        application as DummyDictionaryApplication
    }
    private val viewModelFactory by lazy {
        ViewModelFactory(app.getLoginRepository())
    }
    private val viewModel:LoginViewModel  by viewModels{
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(app.isUserLogin()){
            return startMainActivity()
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.viewModel = viewModel

        viewModel.status.observe(this){status ->
            handleUiState(status)
        }
    }

    private fun handleUiState(status:LoginUIStatus){
        when(status){
            is LoginUIStatus.Error -> Snackbar.make(binding.imageView,
                "Ha sucedido un error"+status.exception.message,
                Snackbar.LENGTH_SHORT).show()

            LoginUIStatus.Loading -> Log.d("Login List Status","Loading")
            LoginUIStatus.Resume -> Log.d("Login List Status","Resume")
            is LoginUIStatus.Success ->{
                app.saveAuthToken(status.token)
                startMainActivity()
            }
        }
    }

    private fun startMainActivity(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}