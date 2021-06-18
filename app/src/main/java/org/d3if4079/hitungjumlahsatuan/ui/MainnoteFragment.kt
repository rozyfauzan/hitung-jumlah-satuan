package org.d3if4079.hitungjumlahsatuan.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4079.hitungjumlahsatuan.databinding.FragmentMainnoteBinding
import org.d3if4079.hitungjumlahsatuan.room.Note
import org.d3if4079.hitungjumlahsatuan.room.NoteDB

class MainnoteFragment : Fragment(){

    val db by lazy { NoteDB(requireContext()) }

     lateinit var noteAdapter: NoteAdapter





    private lateinit var binding: FragmentMainnoteBinding;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentMainnoteBinding.inflate(layoutInflater, container, false)

        noteAdapter = NoteAdapter(arrayListOf(), object : NoteAdapter.OnAdapterListener{
            override fun onDelete(note: Note) {



                deleteDialog(note)



            }

        })

            binding.listNote.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = noteAdapter
            }

        return binding.root


    }

    fun loadNote(){
        CoroutineScope(Dispatchers.IO).launch {
            val notes = db.noteDao().getNotes()
            Log.d("MainnoteFragment","dbresponse $notes")
            withContext(Dispatchers.Main){
                noteAdapter.setData(notes)
            }


        }
    }


    override fun onStart() {
        super.onStart()
          loadNote()
        }

    private fun deleteDialog(note: Note){
        val alert = AlertDialog.Builder(requireContext())
        alert.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin Akan Menghapus ${note.note}  ?")
            setPositiveButton("Tidak"){
                DialogInterface,i -> DialogInterface.dismiss()
            }
            setNegativeButton("Ya"){
                DialogInterface,i -> DialogInterface.dismiss()
                CoroutineScope(Dispatchers.IO).launch {
                    db.noteDao().deleteNote(note)
                    loadNote()

                }

            }

        }
        alert.show()
    }
}

