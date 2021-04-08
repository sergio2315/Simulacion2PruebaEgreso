package com.example.simulacion2pruebaegreso.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.simulacion2pruebaegreso.databinding.FragmentSecondBinding
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
       viewModel.returnDetail().observe(viewLifecycleOwner, Observer {
           it?.let {
               var credit = ""
               if (it.credit){
                   credit = "Acepta crédito"
               }else{
                   credit = "Solo efectivo"
               }
               Glide.with(binding.imageView2).load(it.image).centerCrop().into(binding.imageView2)
               binding.txLastPrice.text = "Antes: "+it.lastPrice
               binding.txActualPrice.text = "Ahora: "+it.price
               binding.txCredit.text = credit
               binding.txDescription1.text = it.description

               val mId = it.id
               val name = it.name
               binding.btnContact.setOnClickListener{
                   sendEmail(name, mId)
               }
           }
       })
    }
    fun sendEmail(name: String, id: Int) {
        val email = arrayOf("info@novaera.cl")
        val subject = "Consulta $name id $id"
        val mesage = "Hola\n" +
                "Vi la propiedad $name de código $id y me gustaría que me\n" +
                "contactaran a este correo o al siguiente número __________\n" +
                "Quedo atento.”"
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_EMAIL, email)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, mesage)

        try {
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "e.message", Toast.LENGTH_LONG).show()
        }

    }
}
