package org.d3if1088.praassesment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3if1088.praassesment.databinding.FragmentPersegiPanjangBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class PersegiPanjangFragment : Fragment() {
    private lateinit var binding : FragmentPersegiPanjangBinding
    private var luas = 0
    private var keliling = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_persegi_panjang, container, false)
        if (savedInstanceState != null){
            luas = savedInstanceState.getInt("luasState")
            keliling = savedInstanceState.getInt("kelilingState")
        }

        view()

        binding.btnHitung.setOnClickListener {
            var lebar = binding.inputLebar.text.toString().toInt()
            var panjang = binding.inputPanjang.text.toString().toInt()

            hitung(lebar,panjang)
        }

        binding.btnShare.setOnClickListener {
            actionShare()
        }

        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("luasState",luas)
        outState.putInt("kelilingState",keliling)
        super.onSaveInstanceState(outState)
    }

    private fun shareHasil() : Intent {
        return ShareCompat.IntentBuilder.from(activity!!)
            .setText(getString(R.string.share_text_persegi_panjang, luas.toString(), keliling.toString()))
            .setType("text/plain")
            .intent
    }

    private fun actionShare(){
        startActivity(shareHasil())
    }

    private fun hitung(lebar : Int, panjang : Int){
        luas = lebar * panjang
        keliling = 2 * (panjang + lebar)

        view()
    }

    private fun view(){
        binding.tvHasilLuas.text = luas.toString()
        binding.tvHasilKeliling.text = keliling.toString()
    }

}
