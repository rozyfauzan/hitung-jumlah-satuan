package org.d3if4079.hitungjumlahsatuan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*
import org.d3if4079.hitungjumlahsatuan.databinding.FragmentTambahnoteBinding
import org.d3if4079.hitungjumlahsatuan.room.Note
import org.d3if4079.hitungjumlahsatuan.room.NoteDB

class TambahnoteFragment : Fragment(){

    val db by lazy { NoteDB(requireContext()) }

    private lateinit var binding: FragmentTambahnoteBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTambahnoteBinding.inflate(layoutInflater, container, false)

        binding.tambahbutton.setOnClickListener{
            CoroutineScope(Dispatchers.IO).launch {
                db.noteDao().addNote(
                    Note(0, binding.noteeditText.text.toString())

                )




            }
        }


        return binding.root
}



}
