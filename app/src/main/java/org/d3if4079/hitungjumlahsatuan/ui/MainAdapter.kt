package org.d3if4079.hitungjumlahsatuan.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if4079.hitungjumlahsatuan.databinding.AdapterListinternetBinding

class MainAdapter : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private val dataDiri = mutableListOf<DataDiri>()

    class ViewHolder(private val binding: AdapterListinternetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(dataDiri: DataDiri) = with(binding) {

        idTextView.text = dataDiri.id
            userIdTextView.text = dataDiri.userId
             titleTextView.text = dataDiri.title
            bodyTextView.text = dataDiri.body


        }
    }

    fun UpdateData(newData : List<DataDiri>){
        dataDiri.clear()
        dataDiri.addAll(newData)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterListinternetBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataDiri[position])
    }

    override fun getItemCount(): Int {
       return dataDiri.size
    }

}