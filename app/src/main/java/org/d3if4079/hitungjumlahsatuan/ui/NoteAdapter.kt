package org.d3if4079.hitungjumlahsatuan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.d3if4079.hitungjumlahsatuan.R
import org.d3if4079.hitungjumlahsatuan.databinding.AdapterNoteBinding
import org.d3if4079.hitungjumlahsatuan.room.Note

class NoteAdapter (private val notes: ArrayList<Note>, private val listener : OnAdapterListener) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder> (){



    class NoteViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val binding = AdapterNoteBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.adapter_note,parent,false)
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        with(holder){
            binding.note.text = notes[position].note
            binding.iconDelete.setOnClickListener{
                listener.onDelete(notes[position])
            }
        }


    }

    override fun getItemCount() =  notes.size


    fun setData(list: List<Note>){

        notes.clear()
        notifyDataSetChanged()
        notes.addAll(list)
    }

interface OnAdapterListener{
    fun onDelete(note: Note)

}

}