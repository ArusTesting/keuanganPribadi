package ariel.paba.keuanganpribadi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PengeluaranAdapter(
    private val daftarPengeluaran: ArrayList<Pengeluaran>,
    private val onEdit: (Int) -> Unit,
    private val onHapus: (Int) -> Unit
) : RecyclerView.Adapter<PengeluaranAdapter.PengeluaranViewHolder>() {

    class PengeluaranViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaPengeluaran)
        val tvDetail: TextView = itemView.findViewById(R.id.tvDetailPengeluaran)
        val btnEdit: Button = itemView.findViewById(R.id.btnEditPengeluaran)
        val btnHapus: Button = itemView.findViewById(R.id.btnHapusPengeluaran)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PengeluaranViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pengeluaran, parent, false)
        return PengeluaranViewHolder(view)
    }

    override fun onBindViewHolder(holder: PengeluaranViewHolder, position: Int) {
        val pengeluaran = daftarPengeluaran[position]

        holder.tvNama.text = pengeluaran.nama
        holder.tvDetail.text =
            "${pengeluaran.kategori} - Rp${pengeluaran.jumlah} - ${pengeluaran.tanggal}"

        holder.btnEdit.setOnClickListener {
            val posisi = holder.bindingAdapterPosition
            if (posisi != RecyclerView.NO_POSITION) onEdit(posisi)
        }

        holder.btnHapus.setOnClickListener {
            val posisi = holder.bindingAdapterPosition
            if (posisi != RecyclerView.NO_POSITION) onHapus(posisi)
        }
    }

    override fun getItemCount(): Int = daftarPengeluaran.size
}