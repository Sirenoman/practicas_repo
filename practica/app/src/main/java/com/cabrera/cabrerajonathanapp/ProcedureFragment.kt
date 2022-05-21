package com.cabrera.cabrerajonathanapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.cabrera.cabrerajonathanapp.databinding.ActivityMainBinding
import com.cabrera.cabrerajonathanapp.databinding.FragmentProcedureBinding

class ProcedureFragment : Fragment() {
    private lateinit var binding: FragmentProcedureBinding
    private val viewModel: MainViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_procedure, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        val navController = findNavController()
        binding.containedButton.setOnClickListener {
            val action = ProcedureFragmentDirections.actionProcedureFragmentToResultFragment2()
            navController.navigate(action)
        }
    }

    companion object {

    }
}