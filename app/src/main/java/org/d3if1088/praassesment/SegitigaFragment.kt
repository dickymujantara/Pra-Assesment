package org.d3if1088.praassesment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import org.d3if1088.praassesment.databinding.FragmentSegitigaBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class SegitigaFragment : Fragment() {
    private lateinit var binding : FragmentSegitigaBinding
    private var luas = 0.0
    private var keliling = 0.0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_segitiga, container, false)

        if (savedInstanceState != null){
            luas = savedInstanceState.getDouble("luasState")
            keliling = savedInstanceState.getDouble("kelilingState")
        }

        view()

        binding.btnHitung.setOnClickListener {
            var alas = binding.inputAlas.text.toString().toDouble()
            var tinggi = binding.inputTinggi.text.toString().toDouble()

            hitung(alas,tinggi)
        }

        binding.btnShare.setOnClickListener {
            actionShare()
        }

        return binding.root
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

    private fun hitung(alas : Double, tinggi : Double){
        luas = (alas * tinggi)/2
        keliling = alas + tinggi + (Math.hypot(alas,tinggi))

        view()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putDouble("luasState",luas)
        outState.putDouble("kelilingState",keliling)
        super.onSaveInstanceState(outState)
    }

    private fun view(){
        binding.tvHasilLuas.text = luas.toString()
        binding.tvHasilKeliling.text = keliling.toString()
    }

}
