package ariel.paba.keuanganpribadi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PemasukanAdapter(
    private val daftarPemasukan: ArrayList<Pemasukan>,
    private val onEdit: (Int) -> Unit,
    private val onHapus: (Int) -> Unit
) : RecyclerView.Adapter<PemasukanAdapter.PemasukanViewHolder>() {

    class PemasukanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaPemasukan)
        val tvDetail: TextView = itemView.findViewById(R.id.tvDetailPemasukan)
        val btnEdit: Button = itemView.findViewById(R.id.btnEditPemasukan)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapusPemasukan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PemasukanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pemasukan, parent, false)
        return PemasukanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PemasukanViewHolder, position: Int) {
        val pemasukan = daftarPemasukan[position]

        holder.tvNama.text = pemasukan.nama
        holder.tvDetail.text =
            "${pemasukan.kategori} - Rp${pemasukan.jumlah} - ${pemasukan.tanggal}"

        holder.btnEdit.setOnClickListener {
            onEdit(holder.adapterPosition)
        }

        holder.btnHapus.setOnClickListener {
            onHapus(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = daftarPemasukan.size
}