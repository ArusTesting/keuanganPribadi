package ariel.paba.keuanganpribadi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ariel.paba.keuanganpribadi.R

class KategoriAdapter(
    private val daftarKategori: ArrayList<Kategori>
) : RecyclerView.Adapter<KategoriAdapter.KategoriViewHolder>() {

    class KategoriViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaKategori)
        val tvJenis: TextView = itemView.findViewById(R.id.tvJenisKategori)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KategoriViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kategori, parent, false)
        return KategoriViewHolder(view)
    }

    override fun onBindViewHolder(holder: KategoriViewHolder, position: Int) {
        val kategori = daftarKategori[position]
        holder.tvNama.text = kategori.nama
        holder.tvJenis.text = kategori.jenis
    }

    override fun getItemCount(): Int = daftarKategori.size
}