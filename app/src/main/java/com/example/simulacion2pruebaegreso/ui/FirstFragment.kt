package com.example.simulacion2pruebaegreso.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.simulacion2pruebaegreso.R
import com.example.simulacion2pruebaegreso.databinding.FragmentFirstBinding
import com.example.simulacion2pruebaegreso.viewModel.PhonesViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: PhonesViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PhonesAdapter()
        binding.rvPhones.adapter = adapter
        binding.rvPhones.layoutManager = GridLayoutManager(context,2)

        viewModel.getPhonesList().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("listado", it.toString())
                adapter.update(it)
            }
        })
        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.selectedDetailPhone(it.id)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        })
    }
}