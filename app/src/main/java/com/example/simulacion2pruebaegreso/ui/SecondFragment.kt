package com.example.simulacion2pruebaegreso.ui

import android.content.Intent
import android.net.Uri
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
import com.bumptech.glide.Glide
import com.example.simulacion2pruebaegreso.R
import com.example.simulacion2pruebaegreso.databinding.FragmentSecondBinding
import com.example.simulacion2pruebaegreso.model.pojo.DetailPhones
import com.example.simulacion2pruebaegreso.viewModel.PhonesViewModel

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: PhonesViewModel by activityViewModels()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                viewModel.getDetailPhonesByID(it.id).observe(viewLifecycleOwner, Observer {
                    Glide.with(binding.imageView2).load(it.image).centerCrop().into(binding.imageView2)
                    binding.txLastPrice.text = "Antes "+it.lastPrice
                    binding.txActualPrice.text = "Ahora "+it.price
                    if (it.credit){
                        binding.txCredit.text = "Acepta crédito"
                    }else{
                        binding.txCredit.text = "No acepta crédito"
                    }

                    binding.txDescription1.text = it.description
                })
            }
        })
        binding.btnContact.setOnClickListener{
            sendEmail()
        }
    }
    private fun sendEmail() {

        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("sergiolillo@udec.cl")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, "sergiolillo@udec.cl")
        intent.putExtra(Intent.EXTRA_SUBJECT, "compra celular")
        intent.putExtra(Intent.EXTRA_TEXT, "deseo comprar este celular")

        try {
            startActivity(Intent.createChooser(intent,"sergiolillo@udec.cl"))
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }
}
