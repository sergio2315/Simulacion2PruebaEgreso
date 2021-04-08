package com.example.simulacion2pruebaegreso.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simulacion2pruebaegreso.databinding.PhonesItemListBinding
import com.example.simulacion2pruebaegreso.model.pojo.Phones

class PhonesAdapter: RecyclerView.Adapter<PhonesAdapter.PhonesAdapterVH>() {
    private var listPhones = listOf<Phones>()
    private val selectedItem  =  MutableLiveData<Phones>()

    fun selectedItem() = selectedItem
    fun update(list: List<Phones>) {
        listPhones = list
        notifyDataSetChanged()
    }
    inner class PhonesAdapterVH(private val binding: PhonesItemListBinding)
        :RecyclerView.ViewHolder(binding.root), View.OnClickListener{
        fun bind(phones: Phones){
            Glide.with(binding.imageView).load(phones.image).centerCrop().into(binding.imageView)
            binding.txName.text = phones.name
            binding.txPrice.text = phones.price.toString()
            itemView.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
            selectedItem().value = listPhones[adapterPosition]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhonesAdapterVH {
        return PhonesAdapterVH(PhonesItemListBinding.inflate(LayoutInflater.from(parent.context)))
    }
    override fun onBindViewHolder(holder: PhonesAdapterVH, position: Int) {
        val phones = listPhones[position]
        holder.bind(phones)
    }
    override fun getItemCount(): Int = listPhones.size

}