package org.d3if4079.hitungjumlahsatuan.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.d3if4079.hitungjumlahsatuan.R
import org.d3if4079.hitungjumlahsatuan.databinding.FragmentJumlahBinding

class JumlahFragment : Fragment() {
    private lateinit var binding: FragmentJumlahBinding;

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.menu_about){
            findNavController().navigate(R.id.action_jumlahFragment_to_aboutFragment);
            return true

        }   else if (item.itemId==R.id.menu_rumus) {
            findNavController().navigate(R.id.action_jumlahFragment_to_rumusFragment);
            return true
        }
            return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJumlahBinding.inflate(layoutInflater, container, false)

        binding.hitungbutton.setOnClickListener {
            val jumlah = binding.jumlaheditText.text.toString().toFloat();
            val pilih = binding.radioGroup2.checkedRadioButtonId;


            if (pilih == R.id.rim_Radio) {
                val rumusRodi = jumlah * 500;
                binding.jumlahkeseluruhanTextview.text =
                    getString(R.string.jumlahkeseluruhan, rumusRodi);

            } else if (pilih == R.id.grossRadio) {
                val rumusGross = jumlah * 144;
                binding.jumlahkeseluruhanTextview.text =
                    getString(R.string.jumlahkeseluruhan, rumusGross);


            } else if (pilih == R.id.lusinRadio) {
                val rumusLusin = jumlah * 12;
                binding.jumlahkeseluruhanTextview.text =
                    getString(R.string.jumlahkeseluruhan, rumusLusin);

            } else if (pilih == R.id.kodiRadio) {
                val rumusKodi = jumlah * 20;
                binding.jumlahkeseluruhanTextview.text =
                    getString(R.string.jumlahkeseluruhan, rumusKodi);

            } else if (pilih == -1) {
                Toast.makeText(context, "Harap Diisi Dengan Lengkap", Toast.LENGTH_SHORT).show();
            }

        }

        binding.sharebutton.setOnClickListener {
            val pilih = binding.radioGroup2.checkedRadioButtonId;
            val satuan =
                if (pilih==R.id.lusinRadio)
                    getString(R.string.lusin)

                else if (pilih==R.id.grossRadio)
                    getString(R.string.gross)

                else if (pilih==R.id.kodiRadio)
                    getString(R.string.kodi)

                else
                    getString(R.string.rim)


            val message= getString(R.string.bagikan_pack,
                binding.jumlaheditText.text,
                satuan,
                binding.jumlahkeseluruhanTextview.text)

            val shareIntent = Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT,message)
            if (shareIntent.resolveActivity(requireActivity().packageManager)!= null){
                startActivity(shareIntent)
            }

        }

        setHasOptionsMenu(true)
        return binding.root
    }
}
